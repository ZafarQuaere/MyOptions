package com.dynamicmethoddispatch;

/**
 * Created by parasmani.sharma on 25/10/2016.
 */
public class Student extends Object{

    Student()
    {
        System.out.println("Student Constructor");
    }

    public void show(){
        System.out.println("Super Class...."+super.getClass().getName().toString());
        System.out.println("Student details.");
    }

}
