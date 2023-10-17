package org.example;

public class SaleJardin extends Thread {
    private RecursoJardin recurso;

    public SaleJardin(String nombre, RecursoJardin recurso){
        this.setName(nombre);
        this.recurso = recurso;
    }

    @Override
    public void run(){
        synchronized (recurso){//para cuando no hayamos el método de recurso nosotros
            recurso.decrementarCuenta();
        }
    }
}
