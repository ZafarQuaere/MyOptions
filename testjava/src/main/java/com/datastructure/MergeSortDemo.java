package com.datastructure;

import java.util.Arrays;

/**
 * Created by zafar.imam on 25-05-2017.
 */

public class MergeSortDemo {

    public static void main(String[] arg){

        int arr[] = {12,11,13,5,6,7};
        printArray("Array before Sorting ",arr);
       // mergeSort(arr);
    }

    private static void printArray(String info, int[] arr) {
        System.out.println(info);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]+" ");
        }
    }
}
