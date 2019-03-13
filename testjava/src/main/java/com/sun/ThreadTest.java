package com.sun;

/**
 * Created by amit on 16-09-2016.
 */
public class ThreadTest
{
    public static void main(String[] args)
    {
        NotThreadSafe sharedInstance = new NotThreadSafe();
        for (int index = 0; index < 100; index++) {
            new Thread(new MyRunnable(sharedInstance, "t" + index)).start();
        }
    }
}
