package com.assertions;

/**
 * Created by zafar.imam on 01-07-2017.
 */

public class Test {
    public static  void main(String arg[]){
    int i=    foo(513,2);
        System.out.print(i);
    }

    private static int foo(int n, int r) {
        if (n >0){
            return (n % r + foo(n/r,r));
        }else {
            return 0;
        }

    }

}
