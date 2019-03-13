package com.logicprogram.oops;

/**
 * Created by parasmani.sharma on 25/07/2017.
 */

class Parent
{
    int a = 10;

    public void m()
    {
        System.out.print("Parent a : " + a);
    }
}

class Child extends Parent
{
    int a = 20 ;
    public void m()
    {
        System.out.print("\nChild a : " + a);
    }
}

public class Inheritance {

    public static void main(String args[])
    {
        Parent p  = new Child();
        int a = p.a;
        System.out.print("Main a : " + a);
        p.m();

    }

}
