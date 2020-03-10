package com.codility;

import java.util.Arrays;
import java.util.Scanner;

public class CyclicRotation {

    public static void main(String[] arg) {
        int[] array1 = new int[]{1, 2, 3, 4};
        System.out.println("Enter no of shift: ");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        shiftArray(array1, i);

    }
    private static void shiftArray(int[] array1, int i) {
        System.out.println("Existing array: " + Arrays.toString(array1)+ " Length: "+array1.length);
        int[] array2 = new int[array1.length];
        for (int j = 0; j < array1.length; j++) {
            if (j == 0){
                array2[j]= array1[array1.length-1];
            }else {
                array2[j] = array1[j-1];
            }
        }
        System.out.println("After Shifting array: " + Arrays.toString(array2));
    }

    /*
    array [1,2,3,4]
    array2 [4,1,2,3]

length = 4
j=1
j=2
j=3

     */
}
