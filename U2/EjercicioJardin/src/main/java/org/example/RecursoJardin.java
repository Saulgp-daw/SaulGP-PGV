package org.example;

public class RecursoJardin {

    private int cuenta;

    public RecursoJardin(){
        this.cuenta = 100;

    }

    public void incrementarCuenta(){
        System.out.println(Thread.currentThread().getName()+"---> Entra en el Jardín");
        this.cuenta++;
        System.out.println("Hay "+getCuenta()+ " personas en el jardín");
    }

    public void decrementarCuenta(){
        System.out.println(Thread.currentThread().getName()+"---> Sale del Jardín");
        this.cuenta--;
        System.out.println("Hay "+getCuenta()+ " personas en el jardín");
    }

    public int getCuenta() {
        return this.cuenta;
    }
}
