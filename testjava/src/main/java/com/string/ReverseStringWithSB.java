package com.string;

/**
 * Created by zafar.imam on 10-10-2017.
 */

public class ReverseStringWithSB {
    public static void main(String ...arg){
        String s = "Optiontown";
        System.out.println("Reverse of "+s+" is : "+reverseString(s));
    }

    private static String reverseString(String s) {
        StringBuilder buffer = new StringBuilder(s);
//        StringBuilder reverse = buffer.reverse();
        return buffer.reverse().toString();
    }
}
