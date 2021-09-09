package com.collection.Map;

import java.util.HashMap;

public class MapAndConcurrentMap extends Thread {
    static HashMap<Integer, String> hm = new HashMap<>();

    public static void main(String[] arg) throws InterruptedException {

        hm.put(100, "A");
        hm.put(101, "B");
        hm.put(102, "C");
        MapAndConcurrentMap obj = new MapAndConcurrentMap();
        obj.start();

        for (Object hmObj: hm.entrySet()) {
            Object s=hmObj;
            System.out.println(s);
            Thread.sleep(1000);
        }
        System.out.println(hm);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            // Child thread trying to add
            // new element in the object
            hm.put(103, "D");
        } catch (InterruptedException e) {
            System.out.println("Child Thread going to add element");
        }
        super.run();
    }

}
