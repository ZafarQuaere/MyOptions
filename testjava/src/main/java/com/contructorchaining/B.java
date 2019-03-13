package com.contructorchaining;

/**
 * Created by parasmani.sharma on 04/11/2016.
 */
public class B extends A {


    B()
    {
        super(10);
        System.out.println("Default Contructor of PostPreIncreDecre");
    }

    B(int i)
    {
        this();
        System.out.println("Argment Contructor of PostPreIncreDecre");
        new A();
    }

    {
        System.out.println("Init block of PostPreIncreDecre");
    }


}
