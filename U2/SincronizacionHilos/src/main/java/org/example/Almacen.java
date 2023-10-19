package org.example;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Almacen {

    private ArrayList<Character> datos = new ArrayList<>();
    private char[] abecedario = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

   public synchronized void guardar(){
       try{
           while(this.datos.size() == 6){
               this.wait();
           }
           generarLetraAleatoria();
           System.out.println("Datos actuales guardados: "+this.datos.size());
           this.notify();
       }catch(InterruptedException ex){
           System.err.println("Error: "+ex);
       }


    }

    public synchronized void sacar(){
        try{
            while(this.datos.isEmpty()){
                this.wait();
            }
            this.datos.remove(0);
            System.out.println("Datos actuales después de sacar: "+this.datos.size());
            this.notify();
        }catch(InterruptedException ex){
            System.err.println("Error: "+ex);
        }

    }

    public boolean comprobarLetraExiste(char letra) {
       return this.datos.contains(letra);
    }

    public void generarLetraAleatoria(){
        Random rand = new Random();
        char letraAleatoria;
       do{
           int indiceAleatorio = rand.nextInt(abecedario.length);
           letraAleatoria = abecedario[indiceAleatorio];

       }while(comprobarLetraExiste(letraAleatoria));
        this.datos.add(letraAleatoria);
    }

}
