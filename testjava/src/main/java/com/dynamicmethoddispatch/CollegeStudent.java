package com.dynamicmethoddispatch;

import java.util.Comparator;

/**
 * Created by parasmani.sharma on 25/10/2016.
 */
public class CollegeStudent extends Student {

    {
        super.show();
    }

    CollegeStudent() {
        super();
        System.out.println("College Student Contructor  ");
    }

    public void show() {
        super.show();
        System.out.println("\nCollege Student details.");
    }

    //main method
    public static void main(String args[]) {
        //Super class can contain subclass object.
        CollegeStudent obj1 = new CollegeStudent();
        //Student obj1 = new Student();

        Student obj2 = (Student) obj1;

        //method call resolved at runtime
        obj2.show();
    }

}
