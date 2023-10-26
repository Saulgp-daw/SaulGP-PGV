package org.example;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class App {
    private static int BOARD_SIZE = 5;
    private static char[][] board;
    private static boolean[][] marked;
    private static int targetRow, targetCol, barco2Row, barco2Col;

    private static int puntUsuario = 0;
    private static int puntMaquina = 0;
    private static boolean gameOver = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean inputIsInt;
        /**
         * AÑADIDO
         * He añadido el do while para que se repite en caso de no introducir un tamaño apropiado. por ejemplo no siendo un número
         */
        do {
            System.out.print("Introduce el tamaño del tablero con el que quieras jugar: ");
            if (scanner.hasNextInt()) {
                BOARD_SIZE = scanner.nextInt();
                inputIsInt = true;
            } else {
                System.out.println("El valor introducido no es un número entero. Inténtalo de nuevo.");
                scanner.next(); // Consumir el valor no entero del Scanner
                inputIsInt = false;
            }
        } while (!inputIsInt);
        scanner.nextLine();
        board = new char[BOARD_SIZE][BOARD_SIZE];
        marked = new boolean[BOARD_SIZE][BOARD_SIZE];
        Semaphore usuario = new Semaphore(1); //Tenemos dos semáforos, uno para el hilo del usuario
        Semaphore maquina = new Semaphore(1);//y otro para el de la maquina


        System.out.println("{ { Tamaño del tablero seleccionado: " + BOARD_SIZE+" } }"); //He añadido el tamaño del tablero en la consola


        initializeBoard();

        /**
         * Para el hilo del usuario, debemos primero adquirir el estado de la máquina para prevenir que salte
         * el while se ejecutará mientras gameover sea falso, es decir cuando la máquina o el usuario maten el barco del otro
         * try acquire devolverá un booleano si ha abierto el semáforo con éxito y realizará un acquire por lo tanto está activo (verde)
         * dentro de este if se ha cambiado el if de adentro por un while para el caso si el usuario spammea enter permite seguir introduciendo caracteres
         * hasta que sean válidos.
         *Luego se comprobará que ambos caracteres introducidos están siempre en el rango de mayúsculas de la tabla ascii y que el número esté entre 1 y el total
         * si sale todo correcto se liberará el hilo de la máquina
         */
        Thread userThread = new Thread(() -> {

            try {
                maquina.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (!gameOver) {
                if(usuario.tryAcquire()){
                    System.out.print("(x) Introduce una letra y un número (ejemplo: A2): ");
                    String input = scanner.nextLine();
                    while (input.length() != 2) {
                        System.out.print("(x) Entrada inválida. Debe ser una letra y un número.");
                        input = scanner.nextLine();
                        //continue;
                    }
                    char letter = input.charAt(0);
                    int number = Character.getNumericValue(input.charAt(1));
                    if (letter >= 'A' && letter < 'A' + BOARD_SIZE && number >= 1 && number <= BOARD_SIZE) {
                        checkGuess(letter - 'A', number - 1);
                    } else {
                        System.out.println("(x) Entrada inválida. Letra entre A y E, número entre 1 y "+BOARD_SIZE);
                    }
                    maquina.release();
                }

            }
            scanner.close();
        });

        /**
         * El hilo de la máquina está puesto apra que espere 1000ms para que el de usuario se ejecute antes
         * dentro del while, la máquina hará un random de la columna y fila que debe elegir, marcará como establecida la casilla en donde
         * ha caido. Finalmente liberará al usuario
         */
        Thread gameThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Random random = new Random();
            while (!gameOver) {
                if(maquina.tryAcquire()){
                    int row = random.nextInt(BOARD_SIZE);
                    int col = random.nextInt(BOARD_SIZE);
                    if (!marked[row][col]) {
                        markCell(row, col);
                    }
                    usuario.release();
                }

            }
        });

        userThread.start();
        gameThread.start();

        try {
            userThread.join();
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * He añadido esto para que salga en consola si ha habido empate o ha ganado alguien
         */
        System.out.println("Fin del juego.");
        System.out.print("Puntuaciónes:");
        if(puntUsuario > puntMaquina){
            System.out.println("Has ganado");
        }else if(puntMaquina > puntUsuario){
            System.out.println("Has perdido");
        }else if(puntMaquina == puntUsuario){
            System.out.println("Empate");
        }
        System.out.println("Usuario: "+puntUsuario+" - Maquina: "+puntMaquina);
    }

    /**
     * Esta función inicializa la posición de los barcos y si se ha disparado o no a esa posición del tablero en concreto
     */
    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '~'; // Agua
                marked[i][j] = false;
            }
        }
        targetRow = (new Random()).nextInt(BOARD_SIZE);
        targetCol = (new Random()).nextInt(BOARD_SIZE);

        /**
         * Aquí es donde he creado el segundo barco, verifico que ninguna de sus dos posiciones coincida con la del otro
         */
        do{
            barco2Row = (new Random()).nextInt(BOARD_SIZE);
            barco2Col = (new Random()).nextInt(BOARD_SIZE);
        }while(barco2Row == targetRow || barco2Col == targetCol);

        System.out.println("Barco1: "+targetRow+" "+targetCol);
        System.out.println("Barco2: "+barco2Row+" "+barco2Col);
    }

    /**
     * Esta función recibirá la posición de columna y fila que el usuario ha tecleado, si coincide plenamente con los valores de target de los barcos lo hunde
     * @param row el número de fila introducida por el usuario, al igual que la máquina
     * @param col el número de columna introducida por el usuario, al igual que la máquina
     */
    private static void checkGuess(int row, int col) {
        if (row == targetRow && col == targetCol || row == barco2Row && col == barco2Col) {
            System.out.println("(x) ¡Tocado y hundido!");
            puntUsuario++;
            if(puntUsuario > 1 || puntUsuario == puntMaquina){ //Añadido, preguntamos para acabar el juego si la puntuación es mayor o giual al de la máquina
                gameOver = true;
            }

        } else if (marked[row][col]) {
            System.out.println("(x) Ya habías seleccionado esta posición. Prueba de nuevo.");
        } else {
            System.out.println("(x) Agua. Inténtalo de nuevo.");
            marked[row][col] = true;
        }
    }

    /**
     * Markcell pertenece al de la máquina, si acierta uno de los dos barcos aumentaremos
     * en uno su puntuación, luego haremos un if que pregunte si su puntuación es 2 o igual a la del usuario
     * @param row el número de fila introducida por la máquina, al igual que usuario
     * @param col el número de columna introducida por la máquina, al igual que usuario
     */
    private static void markCell(int row, int col) {
        if (row == targetRow && col == targetCol || row == barco2Row && col == barco2Col) {
            System.out.println("(o) ¡El enemigo te ha tocado!");
            puntMaquina++;
            if(puntMaquina > 1 || puntUsuario == puntMaquina){ //Modificado aquí
                gameOver = true;
            }
        } else {
            System.out.println("(o) El enemigo ha disparado a la posición " + (char) ('A' + row) + (col + 1) + " - Agua.");
            marked[row][col] = true;
        }
    }
}