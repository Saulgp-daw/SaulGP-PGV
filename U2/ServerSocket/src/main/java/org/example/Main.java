package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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

            // Mensaje que se enviará al servidorç

            String mensaje;
            do{
                System.out.println("Texto: ");
                 mensaje = sc.nextLine();
                String mensajeCliente = mensaje;
                salidaAlServidor.println(mensajeCliente);
            }while(mensaje != "close");




            // Cerrar conexiones
            salidaAlServidor.close();
            clienteSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*

        public class ServidorTCP {
    public static void main(String[] args) {
        int puerto = 12345; // Puerto en el que el servidor escuchará

        try {
            ServerSocket servidorSocket = new ServerSocket(puerto);
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            Socket clienteSocket = servidorSocket.accept(); // Espera a que un cliente se conecte
            System.out.println("Cliente conectado desde: " + clienteSocket.getInetAddress());

            // Flujo de entrada para recibir datos del cliente
            BufferedReader entradaDesdeCliente = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            // Leer el mensaje del cliente
            String mensajeCliente = entradaDesdeCliente.readLine();
            System.out.println("Mensaje del cliente: " + mensajeCliente);

            // Cerrar conexiones
            entradaDesdeCliente.close();
            clienteSocket.close();
            servidorSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
         */
    }
}