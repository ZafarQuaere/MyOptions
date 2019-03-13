package com.sun;

public class MyRunnable implements Runnable{
    NotThreadSafe instance = null;
    String name;

    public MyRunnable(NotThreadSafe instance, String name){
        this.instance = instance;
        this.name = name;
    }

    public void run(){
        for (int index = 0; index < 100; index++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.instance.add(name + "," + index + "\n");
        }

    }
}