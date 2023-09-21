package org.example;

import java.io.BufferedReader;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        //crearProceso("code");
        //crearProceso("code");
        //imprimirFicheroConsola("/home/dam2/Descargas/test.txt");
        procesoPing("ping");
    }

    public static void imprimirFicheroConsola(String ruta){
        ProcessBuilder processBuilder = new ProcessBuilder("cat", ruta);
        try{
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Fin del proceso");
        }catch(IOException | InterruptedException e){
            System.out.println("Error al lanzar el proceso");
            e.printStackTrace();
        }

    }

    //Creamos el proceso. Ejecutamos el programa que le indiquemos por parámetros
    public static void crearProceso(String nombreApp){
        ProcessBuilder processBuilder = new ProcessBuilder(nombreApp, "ls");
        try{
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Fin del proceso");
        }catch(IOException | InterruptedException e){
            System.out.println("Error al lanzar el proceso");
            e.printStackTrace();
        }
    }

    public static void procesoPing(String proceso){
        ProcessBuilder processBuilder = new ProcessBuilder(proceso, "www.google.com");
        try{
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("Fin del proceso");
        }catch(IOException | InterruptedException e){
            System.out.println("Error al lanzar el proceso");
            e.printStackTrace();
        }
    }
}