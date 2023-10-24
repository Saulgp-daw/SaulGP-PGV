package org.example;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class App {
    private static final int BOARD_SIZE = 5;
    private static char[][] board;
    private static boolean[][] marked;
    private static int targetRow, targetCol;
    private static boolean gameOver = false;

    public static void main(String[] args) {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        marked = new boolean[BOARD_SIZE][BOARD_SIZE];
        Semaphore usuario = new Semaphore(1); //Tenemos dos semáforos, uno para el hilo del usuario
        Semaphore maquina = new Semaphore(1);//y otro para el de la maquina
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
            Scanner scanner = new Scanner(System.in);
            try {
                maquina.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (!gameOver) {
                if(usuario.tryAcquire()){
                    System.out.print("Introduce una letra y un número (ejemplo: A2): ");
                    String input = scanner.nextLine();
                    while (input.length() != 2) {
                        System.out.print("Entrada inválida. Debe ser una letra y un número.");
                        input = scanner.nextLine();
                        //continue;
                    }
                    char letter = input.charAt(0);
                    int number = Character.getNumericValue(input.charAt(1));
                    if (letter >= 'A' && letter < 'A' + BOARD_SIZE && number >= 1 && number <= BOARD_SIZE) {
                        checkGuess(letter - 'A', number - 1);
                    } else {
                        System.out.println("Entrada inválida. Letra entre A y E, número entre 1 y 5.");
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
        System.out.println("Fin del juego.");
    }

    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '~'; // Agua
                marked[i][j] = false;
            }
        }
        targetRow = (new Random()).nextInt(BOARD_SIZE);
        targetCol = (new Random()).nextInt(BOARD_SIZE);
    }

    private static void checkGuess(int row, int col) {
        if (row == targetRow && col == targetCol) {
            System.out.println("¡Tocado y hundido! Has ganado.");
            gameOver = true;
        } else if (marked[row][col]) {
            System.out.println("Ya habías seleccionado esta posición. Prueba de nuevo.");
        } else {
            System.out.println("Agua. Inténtalo de nuevo.");
            marked[row][col] = true;
        }
    }

    private static void markCell(int row, int col) {
        if (row == targetRow && col == targetCol) {
            System.out.println("¡El enemigo te ha tocado!");
            gameOver = true;
        } else {
            System.out.println("El enemigo ha disparado a la posición " + (char) ('A' + row) + (col + 1) + " - Agua.");
            marked[row][col] = true;
        }
    }
}