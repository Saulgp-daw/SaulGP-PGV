package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;
    private final int PORT = 54321;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public void waitConnections() {
        try {
            System.out.println("Esperando conexiones de clientes");

            while (true) {
                Socket socket = serverSocket.accept(); // Espera a que un cliente se conecte
                System.out.println("Cliente conectado correctamente");

                // Crea un hilo para manejar la comunicación con el cliente
                ServerSocketThread serverSocketThread = new ServerSocketThread(socket);
                serverSocketThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.waitConnections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
