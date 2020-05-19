package com.java8;

/**
 * Created by zafar.imam on 06-10-2017.
 */

public class InterfaceDemo implements defaultInterf{

    public static void main(String[] arg){
        InterfaceDemo demo = new InterfaceDemo();
        demo.m1();
        demo.m2();
    }
    @Override
    public void m1() {
        System.out.println("m1() of interface default ");
    }

    @Override
    public void m2() {
        System.out.println("m2() of interface default ");
    }
}

interface defaultInterf{
    public void m1();
    default void m2(){ // we can eiter override it or leave it, we can access it from any class
        System.out.println("New default method" + " is added in interface ");
    }
}

interface normal{
    public void mm1();
    public void mm2();
}