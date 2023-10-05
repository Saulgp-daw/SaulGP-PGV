package org.example;

public class CocheRunnable implements Runnable {

    int sleep = 0;

    CocheRunnable(int sleep){
        this.sleep = sleep;
    }

    @Override
    public void run() {
        for(int i = 0; i < 50; i++){
            System.out.println(i);
            try {
                Thread.sleep(this.sleep);
            } catch (InterruptedException e) {
                System.out.print("Error: "+e);
            }
        }
        System.out.println("Ha llegado al final "+ Thread.currentThread().getName());

    }
}
