package com.contructorchaining;

/**
 * Created by parasmani.sharma on 04/11/2016.
 */
public class A {

    A()
    {
        this(10);
        System.out.println("Default Contructor of A");
    }

    A(int i)
    {
        System.out.println("Argment Contructor of A");
    }


    {
        System.out.println("Init block of A");
    }

}
