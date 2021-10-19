package com.arrays;

import java.util.Arrays;

public class MoveAllZeros {

    public static void main(String[] arg) {
        int[] arr = new int[]{0,1,0,3,12,0};
        moveAllZeros(arr);
    }

    private static void moveAllZeros(int[] arr) {
        System.out.println("Array before move: "+ Arrays.toString(arr));
        int[] newArr = new int[arr.length];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0){
                newArr[count] = arr[i];
                count++;
            }
        }
        for (int i = count; i >0 ; i--) {
            newArr[count] = 0;
        }

        System.out.println("Array after move: "+Arrays.toString(newArr));
    }
}
