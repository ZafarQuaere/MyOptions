package com.assertions;

import java.util.Scanner;

/**
 * Created by zafar.imam on 30-06-2017.
 * 1) assert expression;
   2) assert expression1 : expression2;
 */

public class AssertionDemo {

    /*
    Assertion is a statement in java. It can be used to test your assumptions about the program.
     */
    public static void main(String arg[]){
        //While executing assertion, it is believed to be true. If it fails, JVM will throw an error named AssertionError.

        System.out.println("Enter your age :");
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();

        assert  age >= 18 : "Not valid";//if age will less than 18 then assertion error will be thrown and "not valid" will be
        // printed in essertion error .

        System.out.println("Age is : "+age);

    }
}

