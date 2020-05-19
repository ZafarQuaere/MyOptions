package com.adobe_interview;

import static jdk.nashorn.internal.objects.Global.print;

public class PascalTriangle {

    public static void main(String[] arg) {
        int n = 5;
        printPascal(n);
    }

    private static void printPascal1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                System.out.print(i+1+" ");
            }
            System.out.println();
        }
    }

    private static void printPascal(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}
