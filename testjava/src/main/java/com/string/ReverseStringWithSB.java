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
        StringBuffer buffer = new StringBuffer(s);
        StringBuffer reverse = buffer.reverse();
        return reverse.toString();
    }
}
