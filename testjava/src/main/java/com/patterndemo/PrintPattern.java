package com.patterndemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by zafar.imam on 09-07-2017.
 */

public class PrintPattern {

    public static void main(String... arg) throws IOException {
        System.out.println("Enter Number : ");
        int n = 5;
//        Scanner scanner = new Scanner(System.in);
//        if(scanner.hasNextInt()){
//            n = scanner.nextInt();
//        }
//      scanner.close();

        System.out.println();
        System.out.println();

//        printTriangle(n);
         printPattern1(n);
        //printPattern2(n);
       // printPattern3(n);
//        printPattern4(n);


    }

    /**
     *pattern like below
     1 2 3 4 5
     1 2 3 4
     1 2 3
     1 2
     1
     */
    private static void printPattern4(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n-i ; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    /**
     *  1
        1 2
        1 2 3
        1 2 3 4
        1 2 3 4 5
        1 2 3 4
        1 2 3
        1 2
        1
     */
    private static void printPattern3(int n) {
        boolean flag = false;
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i ; j++) {
                System.out.print(j);
            }
            if (i == n){
                flag = true;
            }
            System.out.println();
            if (flag){
                for (int j = n-1; j > 0 ; j--) {
                    for (int  k = 1;  k <= j ;  k++) {
                        System.out.print(k);
                    }
                    System.out.println();
                }
            }
        }
    }

    /**
     *  1
        1 2
        1 2 3
        1 2 3 4
        1 2 3 4 5
     */
    private static void printPattern2(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <i ; j++) {
                System.out.print(j+1);
            }
            System.out.println();
        }
    }

    /**  print patter like
    1
    2 2
    3 3 3
    4 4 4 4
    5 5 5 5 5
     */
    private static void printPattern1(int n) {
        for (int i = 1; i <=n ; i++) {
            for (int j = 0; j < i ; j++) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }




    /**
    print pattern like
    1
   2 2
  3 3 3
 4 4 4 4
5 5 5 5 5
 4 4 4 4
  3 3 3
   2 2
    1
 */
    private static void printTriangle(int n) {
        boolean flag = false;

        for (int i = 1; i <= n; i++) {
            int temp = i;

            for (int j = n - 1; j >= temp; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= temp; k++) {
                System.out.print(i + " ");
            }
            if (i == n) {
                flag = true;
            }
            System.out.println("");

            if (flag){
                for (int l = temp-1; l > 0; l--) {

                    for (int m = 1; m <= temp-l; m++) {
                        System.out.print(" ");
                    }
                    for (int m = l; m >0 ; m--) {
                        System.out.print(l+" ");
                    }

                    System.out.println();
                }
            }
        }

    }
}
