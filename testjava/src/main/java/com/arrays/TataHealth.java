package com.arrays;

import java.util.Arrays;

public class TataHealth {
    static int[] arr1 = {1,2,3,4,5};
    static int n = 2;
    static int k = 0;
    public static void main(String[] arr) {
        swapArr2Place(arr1,n);
    }

    private static void swapArr2Place(int[] arr, int n) {
        System.out.println("Existing Array:"+ Arrays.toString(arr));
        int[] newArr = new int[arr.length];
        for(int i = 2; i < arr.length; i++){
            newArr[k] = arr[i];
            k++;
        }

        for (int i = 0; i < n; i++) {
            newArr[k] = arr[i];
            k++;
        }

        System.out.println("New Array:"+Arrays.toString(newArr));
    }
}
