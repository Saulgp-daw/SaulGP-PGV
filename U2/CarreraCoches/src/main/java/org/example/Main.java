package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*CocheRunnable cocheRunnable1 = new CocheRunnable(0);
        CocheRunnable cocheRunnable2 = new CocheRunnable(0);
        CocheRunnable cocheRunnable3 = new CocheRunnable(0);
        CocheRunnable cocheRunnable4 = new CocheRunnable(0);
        CocheRunnable cocheRunnable5 = new CocheRunnable(500);

        new Thread(cocheRunnable1).start();
        new Thread(cocheRunnable2).start();
        new Thread(cocheRunnable3).start();
        new Thread(cocheRunnable4).start();
        new Thread(cocheRunnable5).start();*/


        CocheThread cocheThread1 = new CocheThread(0);
        CocheThread cocheThread2 = new CocheThread(0);
        CocheThread cocheThread3 = new CocheThread(0);
        CocheThread cocheThread4 = new CocheThread(100);
        CocheThread cocheThread5 = new CocheThread(0);


        cocheThread1.start();
        cocheThread2.start();
        cocheThread3.start();
        cocheThread4.start();
        cocheThread5.start();

    }
}