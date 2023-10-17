package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        RecursoJardin jardin = new RecursoJardin();
        for (int i = 0; i < 10; i++) {
            (new EntraJardin("Entra el hilo"+i, jardin)).start();
        }

        for (int i = 0; i < 10; i++) {
            (new SaleJardin("Entra el hilo"+i, jardin)).start();
        }

        /*ServidorWeb servidor = new ServidorWeb();
        Terminal terminal1 = new Terminal("Terminal01", servidor);
        Terminal terminal2 = new Terminal("Terminal02", servidor);
        Terminal terminal3 = new Terminal("Terminal03", servidor);
        Terminal terminal4 = new Terminal("Terminal04", servidor);

            terminal1.start();
            terminal2.start();
            terminal3.start();
            terminal4.start();*/

    }
}