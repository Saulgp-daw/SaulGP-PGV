package org.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Server server = null;
        // Manejamos la excepción que lanza Server
        try {
            server = new Server();
        } catch (IOException ex) {
            System.err.println("No se ha podido inicializar el servidor");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            server.waitConnections();
        } catch (IOException ex) {
            System.err.println("No se ha podido iniciar la comunicación con el cliente");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }

    }
}