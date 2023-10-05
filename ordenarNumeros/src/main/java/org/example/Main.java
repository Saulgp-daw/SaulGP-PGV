package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();
        String[] arrayTexto = texto.split(" ");
        int[] arrayNumeros = new int[40];

        System.out.print("El array recibido sin ordenar: ");
        for (int i=0;i<arrayTexto.length;i++) {
            arrayNumeros[i] = Integer.parseInt(arrayTexto[i]);
            System.out.print(arrayTexto[i]+" ");
        }

        Arrays.sort(arrayNumeros);
        System.out.print("\nEl array recibido ordenado: ");
        for (int i=0;i<arrayNumeros.length;i++) {
            System.out.print(arrayNumeros[i]+" ");
        }
        System.out.println();


    }
}