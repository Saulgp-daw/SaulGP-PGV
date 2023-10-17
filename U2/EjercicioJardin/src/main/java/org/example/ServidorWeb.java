package org.example;

public class ServidorWeb {
    private int cuenta;

    public ServidorWeb(){
        this.cuenta = 0;
    }

    public synchronized void aumentarCuenta(){
        System.out.println(Thread.currentThread().getName()+"---> Entra en el server");
        this.cuenta++;
        System.out.println("Hay "+getCuenta()+ " personas en el server");
    }

    public int getCuenta() {
        return this.cuenta;
    }
}
