package com.primitive;

/**
 * Created by zafar.imam on 17-12-2016.
 */

public class OperatorTest {

    public static void main(String arg[]){
        int a = 10;
        int b = ++a; //first increment then assign (pre = post)
        System.out.print(a+" : "+b);
        int c = 10;
        int d = c++; //first assign then increment (pre = post)
        System.out.print("\n");
        System.out.print(c+" : "+d);
    }
}
