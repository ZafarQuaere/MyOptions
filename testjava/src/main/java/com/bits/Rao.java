package com.bits;

/**
 * Created by zafar.imam on 02-02-2017.
 */

public class Rao {

    public void m1(String string){
        System.out.println("String ");
    }
    public void m1(Object o1){
        System.out.println("Object ");
    }
}

 class Test{
    public static void main(String [] ar){
        Rao rao = new Rao();
        rao.m1(new Object());
        rao.m1("Rao");
        rao.m1(null);
    }
}