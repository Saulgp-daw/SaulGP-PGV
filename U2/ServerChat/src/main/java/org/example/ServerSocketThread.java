package org.example;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
public class ServerSocketThread extends Thread{
    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;
    private final Object lock = new Object();
    private String mensajeRecibido;

    public ServerSocketThread(DataInputStream recibirDatos, DataOutputStream enviarDatos){
        this.recibirDatos = recibirDatos;
        this.enviarDatos = enviarDatos;
    }
    public void nuevoMensaje(String mensaje) {
        synchronized (lock) {
            // Establecer el nuevo mensaje y notificar al hilo del servidor
            mensajeRecibido = mensaje;
            lock.notify();
        }
    }

    private String readUTF()  {
        String mensaje = "";
        try {
            mensaje = recibirDatos.readUTF();
            //System.out.println(mensaje);
        } catch (IOException ex) {
            System.err.println("Error al leer datos del cliente");
        }
        return mensaje;
    }

    private void writeUTF(String text) {
        try {
            enviarDatos.writeUTF(text);
        } catch (IOException ex) {
            System.err.println("Error al enviar datos al cliente");
        }
    }

    public void run(){
        try {
            writeUTF("Bienvenido al chat");

            while (true) {
                String mensajeRecibido = readUTF();

                // Salir del bucle si el mensaje es "EXIT"
                if ("EXIT".equals(mensajeRecibido)) {
                    break;
                }

                // Enviar el mensaje a todos los clientes conectados
                // (en este caso, solo hay un cliente)
                writeUTF("Cliente: " + mensajeRecibido);
            }

            // Cierra los flujos y el socket cuando el cliente sale
            recibirDatos.close();
            enviarDatos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
