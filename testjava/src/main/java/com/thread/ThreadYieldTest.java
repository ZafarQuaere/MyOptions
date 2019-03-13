package com.thread;

/**
 * Created by amit on 22-08-2017.
 */
public class ThreadYieldTest
{
    public static void main(String args[])
    {
        ChildThread t1 = new ChildThread("t1");
        //t1.setPriority(10);
        t1.start();


        for (int i = 0; i < 1000; i++)
        {
            System.out.println(String.format("parent thread %s : %d", Thread.currentThread().getName(), i));
            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

    }
}

class ChildThread extends Thread
{
    private Thread dependentThread;
    ChildThread(String name)
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
        for (int i = 0; i < 1000; i++)
        {
            System.out.println(String.format("Child thread %s : %d", getName(), i));
            //yield();
            /*try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
