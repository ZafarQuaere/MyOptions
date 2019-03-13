package com.test;

import java.util.Scanner;

/**
 * Created by parasmani.sharma on 05/05/2017.
 */

public class JavaAssertionExample {

    /**
     * Assertion is a statement in java. It can be used to test your assumptions about the program.
     * While executing assertion, it is believed to be true. If it fails, JVM will throw an error named AssertionError.
     * It is mainly used for testing purpose.
     * @param args
     */

    public static void main( String args[] ){

        Scanner scanner = new Scanner( System.in );
        System.out.print("Enter ur age ");

        int value = scanner.nextInt();
        assert value>=1:" Not valid";

        System.out.println("value is "+value);
    }

}
