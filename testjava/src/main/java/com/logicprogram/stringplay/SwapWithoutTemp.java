package com.logicprogram.stringplay;

import java.util.Scanner;

/**
 * Created by parasmani.sharma on 19/07/2017.
 */

public class SwapWithoutTemp {

    /*public static void main(String []s)
    {
        int a,b;
        //Scanner class to read value
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter value of a: ");
        a=sc.nextInt();
        System.out.print("Enter value of b: ");
        b=sc.nextInt();

        System.out.println("Before swapping - a: "+ a +", b: " + b);
        ////without using thrid variable
        a=a+b;  //a=30
        b=a-b;  //b=10
        a=a-b;  //a=20
        //////////////////////
        System.out.println("After swapping  - a: "+ a +", b: " + b);

    }*/

    public static void main(String [] args) {
        System.out.print("1 ");
        synchronized(args){
            System.out.print("2 ");
            try {
                args.wait();
            }
            catch(InterruptedException e){}
        }
        System.out.print("3 ");
    }

}
