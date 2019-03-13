package com.test;

/**
 * Created by zafar.imam on 04-11-2016.
 */

public class ConstructorChaining {

    public static void main(String args[]){
        B b = new B(10);
        new B();
    }
}

class B extends A{
    B(){
        super(10);
        System.out.println("Default Constructor of PostPreIncreDecre");
    }
    B(int i){
        this();
        System.out.println("Arg Constructor of PostPreIncreDecre");
        new A();
    }
    {
        System.out.println("Init Block of PostPreIncreDecre");
    }
}


class A {
    A(){
        this(10);
        System.out.println("Constructor of A");
    }
    A(int i){
        System.out.println("Arg Constructor of A");
    }
    {
        System.out.println("Init Block of A");
    }
}