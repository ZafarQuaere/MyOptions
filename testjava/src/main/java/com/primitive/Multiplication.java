package com.primitive;

/**
 * Created by zafar.imam on 16-12-2016.
 */

public class Multiplication {
    
    public static void main(String args[]){

        // a table form 1 to 10.
        for (int number = 1; number <= 10; number++) {
            System.out.print("\n\n");
            for (int multiply = 1; multiply <=10; multiply++) {
                System.out.println(number+" * "+multiply +" = "+number*multiply);
            }

        }
    }
}
