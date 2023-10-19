package org.example;

public class HiloConsumidor extends Thread{

    private Almacen almacen;

    //Constructor
    public HiloConsumidor(Almacen almacen){
        this.almacen = almacen;
    }

    @Override
    public void run(){
        for (int i = 0; i < 15; i++){
            this.almacen.sacar();
        }
    }
}
