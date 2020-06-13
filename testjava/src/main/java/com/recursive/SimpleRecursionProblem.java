package com.recursive;

public class SimpleRecursionProblem {
    public static int stepsCount = 0;
    public static void main(String[] arg) {
        int n = 5;
        int sum = sumOfN(n);
        System.out.println("sum of "+n+" is "+sum);

        //calculate power using recursion
//        int power = powerOfN(3,4);
//        System.out.println("Power : "+powerOfN(3,1000));
//        System.out.println(stepsCount);
//
//        // fast way to calculate power (reduce step of recursion)
//        System.out.println("Fast Power : "+fastPowerOfN(3,1000));
//        System.out.println(stepsCount);

        // find the path to reach the destination from in matrix
        System.out.println("Path: "+findPath(4,2));
    }

    private static int findPath(int rows, int column) {
        if (rows == 1 || column == 1)
            return 1;
        return findPath(rows,(column-1)) + findPath(column,(rows - 1));
    }

    private static int fastPowerOfN(int a, int b) {
        stepsCount++;
        if (b == 0)
            return 1;

        if (b % 2 == 0)
            return fastPowerOfN(a*a, b/2); // when power is even divide the power by to and square the number

        return a * fastPowerOfN(a, (b-1)); // when power is odd then a* recursive(a, b-1)
    }

    private static int powerOfN(int a, int b) {
        stepsCount++;
            if (b == 0)
                return 1;
        return a* powerOfN(a,(b-1));
    }

    // sum of n natural numbers
    private static int sumOfN(int n) {
        if (n == 1)
            return  1;
        return n + sumOfN(n -1);
    }
}
