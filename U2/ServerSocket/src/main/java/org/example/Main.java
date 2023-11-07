package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.io.BufferedReader;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String servidorHost = "172.26.9.0"; // Cambia esto a la dirección IP o nombre de host del servidor
        int puerto = 60006; // Puerto del servidor
        Scanner sc = new Scanner(System.in);

        try {
            Socket clienteSocket = new Socket(servidorHost, puerto);

            // Flujo de salida para enviar datos al servidor
            PrintWriter salidaAlServidor = new PrintWriter(clienteSocket.getOutputStream(), true);

            // Flujo de entrada para recibir datos del servidor
            BufferedReader entradaDesdeServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            String mensaje;
            do {
                System.out.println("Texto: ");
                mensaje = sc.nextLine();
                salidaAlServidor.println(mensaje);

                // Leer la respuesta del servidor
                String respuestaDelServidor = entradaDesdeServidor.readLine();
                System.out.println("Servidor: " + respuestaDelServidor);

            } while (!mensaje.equals("EXIT"));

            // Cerrar conexiones
            salidaAlServidor.close();
            entradaDesdeServidor.close();
            clienteSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}