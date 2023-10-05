package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class FibonacciRunnable implements Runnable{

    private int cantidad;

    FibonacciRunnable(int cantidad){
        this.cantidad = cantidad;
    }
    @Override
    public void run() {
        int suma = 0;
        int num1 = 1;
        int num2 = 1;
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(num1, num2));
        for (int i = 1; i < this.cantidad; i++){
            suma = array.get(i) + array.get(i-1);
            array.add(suma);
        }

        array.forEach( num -> System.out.print(num + " "));
    }
}
