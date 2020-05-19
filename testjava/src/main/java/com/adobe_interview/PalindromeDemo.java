package com.adobe_interview;

public class PalindromeDemo {

    public static void main(String[] arg){
        String str = "ABABA";
        checkPalindrome(str);
    }

    private static void checkPalindrome(String str) {
        if (str.length() < 2)
            return;

       /* System.out.println(str.substring(1));
        System.out.println(str.charAt(0));
        System.out.println();*/
       // we reverse the string by using SB or
      /*  StringBuilder builder = new StringBuilder(str);
        StringBuilder temp = builder.reverse();*/
       String temp = reverseStringRecusively(str);
        if (str.equals(temp.toString()))
            System.out.println("String is palindrome");
        else
        System.out.println("String is not palindrome");
    }

    private static String reverseStringRecusively(String str) {
        System.out.println(str);
        System.out.println();
        //base case to handle one char string and empty string
        if (str.length() < 2) {
            return str;
        }

        //ababa // 0,1,2,3,4
       // System.out.println(str.substring(1)+ str.charAt(0));
        return reverseStringRecusively(str.substring(1)) + str.charAt(0);

    }
}
