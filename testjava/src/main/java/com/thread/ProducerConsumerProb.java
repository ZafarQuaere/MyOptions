package com.thread;

public class ProducerConsumerProb {

    public static void main(String [] arg)throws InterruptedException{
        // Object of a class that has both produce()
        // and consume() methods
        PC pc = new PC();

        //create Producer thread
        Thread pThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //create Consumer thread
        Thread cThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        pThread.start();
        cThread.start();

        // pThread finishes before cThread
        pThread.join();
        cThread.join();
    }


    // This class has a list, producer (adds items to list and consumer (removes items).
    public static class PC{


        public void produce() throws InterruptedException{

        }


        public void consume() throws InterruptedException{

        }
    }
}
