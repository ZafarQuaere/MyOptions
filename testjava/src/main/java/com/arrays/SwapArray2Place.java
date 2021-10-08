package com.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/*
TataHealth program
 */
public class SwapArray2Place {
    static int[] arr1 = {1,2,3,4,5};// {3,4,5,1,2}
    static Integer[] arr2 = {1,2,3,4,5};// {3,4,5,1,2}
    static int k = 0;
    static int n = 2;
    public static void main(String[] arg) {
        swapArray2Position(arr1,n);
        System.out.println();
        swapArray1Position(arr1);
        System.out.println();
        swapArray1PositionCollection(arr2);
    }

    private static void swapArray1PositionCollection(Integer[] arr) {
        List<Integer> list = Arrays.asList(arr);
        Collections.swap(list,0,list.size()-1);
//        Integer[] arr1 =(Integer[]) list.toArray();
        System.out.println("After collection swap: "+Arrays.toString(arr));

    }

    private static void swapArray1Position(int[] arr) {
        System.out.println("Before 1 swap: "+Arrays.toString(arr));
        int temp = arr[0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }
        System.out.println("After 1 swap: "+Arrays.toString(arr));
    }

    private static void swapArray2Position(int[] arr1, int n) {
        System.out.println("Existing array: "+ Arrays.toString(arr1));
        int[] nArr = new int[arr1.length];

        for (int i = 2; i < arr1.length; i++) {
            nArr[k] = arr1[i];
            k++;
        }

        for (int i = 0; i < n; i++) {
            nArr[k] = arr1[i];
            k++;
        }

        System.out.println("new Array: "+Arrays.toString(nArr));
    }
}
