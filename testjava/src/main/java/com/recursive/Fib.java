package com.recursive;

/**
 * Created by amit on 09-11-2016.
 */
public class Fib {
    public static void main(String args[]) {
        System.out.println(fib(5));
    }

    private static int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
