package org.example;

import java.sql.Array;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int numAleatorios[]  = new int[40];
        for (int i = 0; i < 40; i++) {
            numAleatorios[i] = (int)(Math.random() * 100);
        }

        for (int num: numAleatorios) {
            System.out.print(num+" ");
        }

    }
}