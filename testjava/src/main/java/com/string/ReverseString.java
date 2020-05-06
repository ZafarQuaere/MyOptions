package com.string;

/**
 * Created by amit on 16-06-2017.
 */
public class ReverseString {
    public static void main(String args[]) {
       // System.out.println(getReverse("ReverseThisString"));
        System.out.println(getReverse("Mine"));
        reverseString("ReverseThisString");
    }

    private static void reverseString(String string) {

    }


    private static String getReverse(String string) {
        char val[] = new char[string.length()];
       // char val[] = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            if (val[i] != 0) {
                break;
            }
            val[i] = string.charAt(string.length() - 1 - i);
            val[string.length() - 1 - i] = string.charAt(i);
            System.out.println("loop count : " + (i + 1) + ", " + (new String(val)));
        }
        return new String(val);
    }
}
