package com.gfg;

public class SimpleProblem {

    public static void printAlternate1(int arr[], int n) {
       /* int i = 0;
        while (i < n) {
            System.out.println(arr[i]);
            i++;
        }*/
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] +" ");
            i++;
        }

    }

    public static void main(String[] arg) {
//        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        int arr[] = {887, 778, 916, 794 ,336, 387, 493 ,650 ,422 ,363 ,28 ,691 ,60 ,764 ,927 ,541,
                427 ,173 ,737, 212, 369 ,568, 430 ,783,  527};
        printAlternate1(arr, arr.length);
        System.out.println();
        printAlternate2(arr, arr.length);
    }

    private static void printAlternate2(int[] arr, int length) {
        if (arr != null && length > 1){
            for (int i = 0; i < length; i++) {
                if (i%2==0){
                    System.out.print(arr[i] +" ");
                }
            }
        }
    }
}
