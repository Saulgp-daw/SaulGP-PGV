package org.example;

public class Terminal extends Thread{
    private ServidorWeb servidor;

    public Terminal(String nombre, ServidorWeb servidor){
        this.setName(nombre);
        this.servidor = servidor;
    }

    @Override
    public void run(){
        for (int i = 0; i < 4; i++) {
            servidor.aumentarCuenta();
        }

    }
}
