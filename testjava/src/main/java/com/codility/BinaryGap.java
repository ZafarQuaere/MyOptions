package com.codility;

import java.util.Arrays;
import java.util.Scanner;

//For description refer file 1
public class BinaryGap {

    public static void main(String[] arg) {
       /* System.out.println("Enter Any Number ");
        Scanner scanner = new Scanner(System.in);
        BinaryGap obj = new BinaryGap();
        int i = scanner.nextInt();
        int[] binaryArray = decToBinary(i);
        for (int j = 0; j < binaryArray.length; j++) {
            System.out.print(binaryArray[j]);
        }*/

         convertNumericeToBinary(15);
    }

    private static void convertNumericeToBinary(int n) {
        int count = 0, a;
        String x = "";
        while(n > 0)
        {
            a = n % 2;
            if(a == 1)
            {
                count++;
            }
            x = a + "" + x;
            n = n / 2;
        }
        System.out.println("Binary number :"+x);
        System.out.println("No. of 1s:"+count);
    }


    // function to convert decimal to binary
    static int[] decToBinary(int n) {
        // array to store binary number
        int[] binaryNum = new int[1000];

        // counter for binary array
        int i = 0;
        while (n > 0) {
            // storing remainder in binary array
            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }

        int[] newArray =new  int[i-1];
        // printing binary array in reverse order
        for (int j = i - 1; j >= 0; j--) {
            System.out.print(binaryNum[j]);
            newArray[newArray.length-i] = binaryNum[j];
        }
        System.out.println();
       // System.out.print(Arrays.asList(arr).toString());
        System.out.println();
        return newArray;
    }

}
