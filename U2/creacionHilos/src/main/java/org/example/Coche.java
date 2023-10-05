package org.example;

public class Coche implements Runnable /* extends Thread*/{

    private String symbol;

    public Coche(String symbol){
        this.symbol = symbol;
    }
    @Override
    public void run() {
        while(true){
            System.out.println(this.symbol);
        }
    }
}
