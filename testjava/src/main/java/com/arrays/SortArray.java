package com.arrays;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class SortArray {
    private static int temp;

    public static void main(String[] args) {
        sortArray();
       sort0s1s2s();

    }

    // sort any array
    private static void sortArray() {
        int count;
        int[] array =  { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
//        int[] array = {0, 1, 2, 0, 1, 2};

        System.out.println(Arrays.toString(array));
        sortTheArray(array);
        // OR
        Arrays.sort(array);
        System.out.println("Sorted Array : " + Arrays.toString(array));
    }

    private static void sortTheArray(int[] num) {

        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] > num[j]) {
                    temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }

        System.out.println("Array Elements in Ascending Order: " + Arrays.toString(num));

    }

    private static void sort0s1s2s() {
        int[] array =  { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
        int n = array.length;
        sort0s1s(array,n);
    }

    private static void sort0s1s(int[] array, int n) {
        System.out.println("Before sorting array : "+Arrays.toString(array));
        int count0=0, count1 = 0, count2 =0;

        for (int i = 0; i < n; i++) {
            switch (array[i]){
                case 0:
                    count0++;
                    break;
                case 1:
                    count1++;
                    break;
                case 2:
                    count2++;
                    break;
            }
        }

        int i = 0;

        // insert all zeros
        while (count0 > 0){
            array[i++] = 0;
            count0--;
        }

        //insert all 1's
        while (count1 > 0) {
            array[i++] = 1;
            count1--;
        }

        // insert all 2's
        while (count2 > 0) {
            array[i++] = 2;
            count2--;
        }

        System.out.println("Sorted Array: "+Arrays.toString(array));
    }

}
