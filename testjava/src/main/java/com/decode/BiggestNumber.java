package com.decode;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by zafar.imam on 26-07-2017.
 * WAP to find the max, min, average and total of numbers entered by the user
 */

public class BiggestNumber {

    public static void main(String arg[]){

        int numbers[] = readNumberFromCommand();
        System.out.println(Arrays.toString(numbers));
        int largest = findLargestNumber(numbers);
        System.out.println("Largest number is : "+largest);
    }

    private static int findLargestNumber(int[] numbers) {
        int biggest = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
           if (biggest < numbers[i]){
               biggest = numbers[i];
           }
        }
        return biggest;
    }

    private static int[] readNumberFromCommand() {
        System.out.println("Enter Array length : ");
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();//getting length of array
        scanner.nextLine();//to avoid new line.

        int arr[] = new int[count];

        System.out.println("Enter Numbers : ");
        Scanner numbScanner = new Scanner(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            if (numbScanner.hasNext()){
                arr[i] = numbScanner.nextInt();
            }

        }


        return arr;
    }
}
