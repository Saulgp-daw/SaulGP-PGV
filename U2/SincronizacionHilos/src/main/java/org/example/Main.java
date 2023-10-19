package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        HiloProductor productor = new HiloProductor(almacen);
        HiloConsumidor consumidor = new HiloConsumidor(almacen);

        productor.start();
        consumidor.start();
    }
}