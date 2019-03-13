package com.thread;

/**
 * Created by amit on 22-08-2017.
 */
public class ThreadJoinTest
{
    public static void main(String args[]) throws InterruptedException {

        MyThread t1 = new MyThread("A");
        MyThread t2 = new MyThread("B");
        MyThread t3 = new MyThread("C");
        t3.setDependentThread(t2);
        t2.setDependentThread(t1);
        //t1.setDependentThread(t3);
        t1.start();
        t2.start();
        t3.start();



    }
}

class MyThread extends Thread
{
    private Thread dependentThread;
    MyThread(String name)
    {
        this.setName(name);
    }

    public void setDependentThread(Thread dependentThread)
    {
        this.dependentThread = dependentThread;
    }

    public void run()
    {
        try {
            if(dependentThread != null)
                dependentThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++)
        {
            System.out.println(String.format("Child thread %s : %d", getName(), i));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
