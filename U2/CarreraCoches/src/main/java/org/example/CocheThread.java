package org.example;

public class CocheThread extends Thread {

    int sleep = 0;

    CocheThread(int sleep){
        this.sleep = sleep;
    }

    @Override
    public void run() {
        for(int i = 0; i < 50; i++){
            System.out.println(i);
            try {
                sleep(this.sleep);
            } catch (InterruptedException e) {
                System.out.print("Error: "+e);
            }
        }
        System.out.println("Ha llegado al final "+ currentThread().getName());

    }
}
