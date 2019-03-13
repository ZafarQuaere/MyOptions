package com.patterndemo;

/**
 * Created by zafar.imam on 04-08-2017.

 */

public class FluidTriangle {
    static int num = 1;
    public static void main(String arg[]){
        int n = 5;
        printFluid(n);
    }
/*
* 1
 * 2 3
 * 4 5 6
 * 7 8 9 10
 * 11 12 13 14 15
 */
    private static void printFluid(int n) {

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= i; j++) {
                System.out.print(num+" ");
                num++;
            }
            System.out.println();
        }
    }
}
