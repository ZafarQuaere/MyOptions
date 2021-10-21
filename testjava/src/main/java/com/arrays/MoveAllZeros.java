package com.arrays;

import java.util.Arrays;

public class MoveAllZeros {

    public static void main(String[] arg) {
        int[] arr = new int[]{0,1,0,3,12,0};
        moveAllZeros(arr);
        System.out.println();
        moveAllZeros2(arr);
    }

    private static void moveAllZeros2(int[] arr) {
        int writer = 0;
        int reader = 0;
        System.out.println("moveAllZeros2 Array before move: "+ Arrays.toString(arr));
        while (reader < arr.length){
            if (arr[reader] != 0){
                arr[writer] = arr[reader];
                writer++;
            }
            reader++;
        }
        while (writer < arr.length){
            arr[writer] = 0;
            writer++;

        }
        System.out.println("moveAllZeros2 Array after move: "+Arrays.toString(arr));
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
