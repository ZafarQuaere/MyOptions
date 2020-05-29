package com.recursive;

import java.util.Scanner;

/**
 * Created by amit on 19-10-2016.
 */
public class Factorials {
    public static void main(String args[]) {
        System.out.println("Enter any number :");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println("fact is : " + fact(num));
    }

    private static int fact(int n) {
        if (n == 0)
            return 1;
        return n * fact(n - 1);
    }
}
