package org.example;

public class HiloProductor extends Thread {
    private Almacen almacen;

    //Contructor
    public HiloProductor(Almacen almacen){
        this.almacen = almacen;
    }

    @Override
    public void run(){
        for (int i = 0; i < 15; i++){
            this.almacen.guardar();
        }
    }
}
