package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        FibonacciRunnable fibonacciRun = new FibonacciRunnable(10);
        FibonacciThread fibonacciThread = new FibonacciThread(10);
        new Thread(fibonacciRun).start();
        fibonacciThread.run();
    }
}