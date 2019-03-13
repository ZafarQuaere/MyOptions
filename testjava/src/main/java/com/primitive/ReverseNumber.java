package com.primitive;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by zafar.imam on 16-07-2017.
 */

public class ReverseNumber {


    private static void reverseTwoDigits(int reverseN) {
        int rev = 0, i = 2;
        for (int j = 0; j < 6; j++) {
            rev = rev * 10;
            rev = rev + reverseN % 10;
            reverseN = reverseN / 10;
        }
        System.out.println("Reverse Two :" + rev);
    }

    public static void main(String arg[]) {
        System.out.print("Enter Number to reverse : ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int reverse = new ReverseNumber().reverseNumber(n);
        System.out.println("Reversed Number is :" + reverse);
        checkPalindrome(n, reverse);
        countDigitsOfNumber(n);

       // printRandomNumbers();

        //  reverseTwoDigits(n);

    }

    private static void printRandomNumbers() {
        System.out.println("Random numbers are : ");
        /*
        Random number logic
        1)randomNum = minimum + (int)(Math.random() * maximum);
        2)Random rn = new Random();
        int n = maximum - minimum + 1;
        int i = rn.nextInt() % n;
        randomNum =  minimum + i;
         */

        Random rn = new Random();
        int maximum = 50, minimum = 1, randomNum;

        //this wil print positive random numbers between 1 and 50
        for (int i = 0; i < 50; i++) {
            System.out.print(minimum + (int)(Math.random() * maximum)+" ");
        }

        System.out.println();

        //this wil print positive/negative random numbers between 1 and 50
        for (int i = 0; i < 50; i++) {
            int n = maximum - minimum + 1;
            int l = rn.nextInt() % n;
            randomNum = minimum + l;
            System.out.print(randomNum+" ");

        }
    }

    private static void checkPalindrome(int n, int reverse) {
        if (n == reverse) {
            System.out.println("Number is palindrome");
        } else {
            System.out.println("Number is not palindrome");
        }
    }

    private static void countDigitsOfNumber(int n) {
        int count = 0, m = n;
        while (n != 0) {
            n = n / 10;
            count++;
        }

        System.out.println("Number of digits in " + m + " is : " + count);
    }

    public int reverseNumber(int n) {

        int reverse = 0;
        while (n != 0) {
            reverse = reverse * 10;
            reverse = reverse + n % 10;
            n = n / 10;
        }


        return reverse;
    }

    public void noUse() {

         /*String arr[] ={"1234","","3355"};
        StringBuilder url = new StringBuilder("https://192.168.64.10/getSellerList.do?mobileAction=SelectUTO&isSelected=0_0_0"+"_");
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i] != null && (!arr[i].equals(""))){
                url.append(arr[i]);
                url.append("*");
            }
        }
        System.out.println(url);*/
    }
}
