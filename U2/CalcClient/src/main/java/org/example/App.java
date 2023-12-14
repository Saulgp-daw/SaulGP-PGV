package org.example;

import java.io.IOException;
import java.rmi.UnknownHostException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        String host = "172.26.13.0";
        int port = 54321;
        Client cliente = null;
        try {
            cliente = new Client(host, port);
            cliente.connect();
        } catch (IOException ex) {
            System.err.println("No se ha podido conectar al Servidor con HOST: "+host+" y PORT: "+port);
            System.err.println(ex.getMessage());
        }
    }
}
