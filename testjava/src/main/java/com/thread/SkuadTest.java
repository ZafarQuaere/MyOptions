package com.thread;

public class SkuadTest extends Thread {

    final Counter x;

    SkuadTest(Counter c) {
        x = c;
    }

    public static void main(String[] arg) throws InterruptedException {
        Counter counter = new Counter();
        SkuadTest t = new SkuadTest(counter);
        SkuadTest t2 = new SkuadTest(counter);

        t.start();
        t2.start();

        t.join();
        t2.join();

        System.out.println(counter.c);
    }

    public void run() {
        x.increment();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x.decrement();
    }
}

class Counter {
    int c = 0;

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }
}
