package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleChatServer {

    private static final int PORT = 12345;
    private static final List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(writer);

                Thread handlerThread = new Thread(new ClientHandler(clientSocket, writer));
                handlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter writer;

        public ClientHandler(Socket socket, PrintWriter writer) {
            this.clientSocket = socket;
            this.writer = writer;
        }

        @Override
        public void run() {
            try {
                Scanner scanner = new Scanner(clientSocket.getInputStream());

                while (true) {
                    if (scanner.hasNextLine()) {
                        String message = scanner.nextLine();
                        System.out.println("Received message: " + message);

                        // Broadcast the message to all clients
                        for (PrintWriter client : clients) {
                            client.println(message);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
