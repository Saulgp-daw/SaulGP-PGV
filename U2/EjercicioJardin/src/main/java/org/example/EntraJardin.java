package org.example;

public class EntraJardin extends Thread {
    private RecursoJardin recurso;

    public EntraJardin(String nombre, RecursoJardin recurso){
        this.setName(nombre);
        this.recurso = recurso;
    }

    @Override
    public void run(){
        synchronized (recurso){ //para cuando no hayamos el método de recurso nosotros
            recurso.incrementarCuenta();
        }

    }
}
