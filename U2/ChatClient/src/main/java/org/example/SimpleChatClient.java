package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleChatClient {

    private static final String SERVER_ADDRESS = "172.26.13.0";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            // Read from console and send to the server
            Scanner consoleInput = new Scanner(System.in);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Thread readerThread = new Thread(() -> {
                try {
                    Scanner serverInput = new Scanner(socket.getInputStream());
                    while (serverInput.hasNextLine()) {
                        String serverMessage = serverInput.nextLine();
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readerThread.start();

            // Read input from the console and send it to the server
            while (true) {
                String message = consoleInput.nextLine();
                writer.println("User 1: "+message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
