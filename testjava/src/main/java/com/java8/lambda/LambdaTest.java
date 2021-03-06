package com.java8.lambda;

import java.util.EventListener;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;


public class LambdaTest implements Interface1, Interface2, EventListener {
    public static void main(String[] args) {
        LambdaTest test = new LambdaTest();
        Interface1 s1 = message -> "Hello1 : " + message;

        Interface1 s2 = message -> ("Hello2 : " + message);

        Interface1.staticMethod();
        s1.toString();
        //s1.defaultMethod();
        System.out.println(s1.sayMessage("Java 1.8"));
        System.out.println(s2.sayMessage("Java 1.8"));

        test.sayMessage("Java 1.8");


        useLambdaInThread();

        test.defaultMethod();

        Iterator iterator;
    }

    private static void useLambdaInThread() {
        Runnable r = () -> System.out.println("Lambda >> Thread");
        Thread t = new Thread(r);
        t.start();
    }


    @Override
    public String sayMessage(String message) {
        System.out.println("Std >> Hello : " + message);
        return "Std >> Hello : " + message;
    }

    @Override
    public void defaultMethod() {
        System.out.println("i am default");
    }




    int test(Comparable s) {
        return 0;
    };

    String test(String j) {
        return "";
    };
}

interface Interface1 {

    String sayMessage(String message);

    // we can define the methods inside interface in java8 by using default keyword.
    default void defaultMethod() {
        System.out.println("i am default1");
    }

    @Override
    public String toString();


    // we can have static method inside interface in java8
    static void staticMethod() {
        System.out.println("staticMethod() called");
    }
}

interface Interface2 {
    default void defaultMethod() {
        System.out.println("i am default2");
    }
}

interface X {
    Iterable m(Object arg);
}

interface Y {
    String m(Iterable arg);
}

interface Z extends X, Y {
}

class test implements X, Y {


    @Override
    public Iterable m(Object arg) {
        return null;
    }

    @Override
    public String m(Iterable arg) {
        return null;
    }
}
