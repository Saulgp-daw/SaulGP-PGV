package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerSocketThread extends Thread{
    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;
    public ServerSocketThread(DataInputStream recibirDatos, DataOutputStream enviarDatos){
        this.recibirDatos = recibirDatos;
        this.enviarDatos = enviarDatos;
    }

    private void readUTF() {
        try {
            System.out.println(Colors.ANSI_RED+recibirDatos.readUTF()+Colors.ANSI_RESET);
        } catch (IOException ex) {
            System.err.println("Error al leer datos del cliente");
        }
    }

    private void writeUTF(String text) {
        try {
            enviarDatos.writeUTF(text);
        } catch (IOException ex) {
            System.err.println("Error al enviar datos al cliente");
        }
    }

    private int readINT() {
        int num = 0;
        try {
            num = recibirDatos.readInt();
            System.out.println(Colors.ANSI_RED+num+Colors.ANSI_RESET);
        } catch (IOException ex) {
            System.err.println("Error al leer datos del cliente");
        }
        return num;
    }

    private void operacion(int opcion) {
        writeUTF("Por favor introduce el primer número:");
        int num1 = readINT();



        writeUTF("Por favor introduce el segundo número:");
        int num2 = readINT();
        switch (opcion) {
            case 1:
                writeUTF("El resultado de la suma es: "+(num1+num2));
                break;
            case 2:
                writeUTF("El resultado de la resta es: "+(num1-num2));
                break;
            case 3:
                writeUTF("El resultado de la multiplicación es: "+(num1*num2));
                break;
            case 4:
                writeUTF("El resultado de la división es: "+(num1/num2));
                break;
            case 5:
                writeUTF("El resultado del módulo es: "+(num1%num2));
                break;
        }
    }

    public void run(){
        writeUTF("¡Bienvenido a tu calculadora!");
        // SUMAR, RESTAR, MULTIPLICAR, DIVIDIR Y MÓDULO + SALIR
        writeUTF("Selecciona una opción:");
        int opcion = -1;
        while (opcion != 0) {
            writeUTF("\t1.- SUMAR\n\t2.- RESTAR\n\t3.- MULTIPLICAR\n\t4.- DIVIDIR\n\t5.- MÓDULO\n\t0.- SALIR");
            opcion = readINT();
            switch (opcion) {
                case 0:
                    writeUTF("Gracias por utilizar la calculadora.");
                    System.out.println("Cliente desconectado.");
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    operacion(opcion);
                    break;
                default:
                    writeUTF("Opción incorrecta.");
            }
        }
    }
}
