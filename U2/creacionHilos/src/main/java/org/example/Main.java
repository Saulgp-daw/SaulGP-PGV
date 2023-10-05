package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
       Coche coche = new Coche("+");
       Coche coche2 = new Coche(" * ");
       Coche coche1 = new Coche(" - ");

       //coche1.start();
        //coche1.run();
       // coche2.run();

        //Si ponemos coche que es clase Runnable dentro de un new Thread tendrá de nuevo el método start, cosa que el otro no tiene
        new Thread(coche).start();
        new Thread(coche1).start();
        new Thread(coche2).start();
    }
}