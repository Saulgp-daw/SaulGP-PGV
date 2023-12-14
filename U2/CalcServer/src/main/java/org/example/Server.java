// Clase que actuará como Servidor de nuestro socket TCP.
package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // Creamos un objeto de tipo ServerSocket
    private final ServerSocket serverSocket;
    // Declaramos un puerto donde se inicializará nuestro servidor.
    private final int PORT = 54321;

    // Declaramos dos objetos. DataInputStream para recibir datos y DataOutputStream para enviarlos.


    // Implementamos el constructor e inicializamos la variable serverSocket.
    public Server() throws IOException {
        // Inicializamos serverSocket indicando el puerto
        this.serverSocket = new ServerSocket(PORT);
        // El constructor de ServerSocket lanza una excepción asi que la añadimos al método
        // para que sea el main quien la tenga que controlar.
    }

    // En este punto ya tendríamos nuestro servidor abierto.
    // Ahora creamos un método que lo que hará será esperar a que un cliente se conecte.
    public void waitConnections() throws IOException {
        while (true) {
            System.out.println("Esperando conexiones de clientes");
            // Creamos un socket y accept() nos devolverá un objeto de tipo socket
            // que será el cliente
            Socket socket = serverSocket.accept(); // EL CLIENTE QUE SE NOS CONECTA
            System.out.println("Cliente conectado correctamente");
            // El servidor se bloquea hasta que se conecte un cliente
            // Flujo de datos
            // Inicializamos ambos objetos

            ServerSocketThread serverSocketThread  =new ServerSocketThread(new DataInputStream(socket.getInputStream()), new DataOutputStream(socket.getOutputStream()));
            serverSocketThread.start();
        }
    }




    }
