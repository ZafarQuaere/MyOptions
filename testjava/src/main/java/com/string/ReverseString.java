package com.string;

import java.util.Stack;

/**
 * Created by amit on 16-06-2017.
 */
public class ReverseString {
    public static void main(String args[]) {
       // System.out.println(getReverse("ReverseThisString"));
        System.out.println(getReverse("Mine"));
        reverseWords("i like this program very much ");
        System.out.println();
        System.out.println();
        reverseWordsUsingLoop("i like this program very much ");
    }

    private static void reverseWordsUsingLoop(String s) {
        System.out.println("Original String: "+s);
        String str = s.trim();
        String[] sArr = str.split(" ");
        int start = 0, end = sArr.length - 1;
        String temp;
        while (start < end) {
            temp = sArr[start];
            sArr[start] = sArr[end];
            sArr[end] = temp;
            start++;
            end--;
        }
        StringBuilder sb = new StringBuilder();
        for (String string : sArr) {
            sb.append(string+ " ");
        }
        System.out.println("Reversed String words : "+sb.toString());
    }

    private static void reverseWords(String string) {
        System.out.println("Original String: "+string);
        String[] sArr = string.split(" ");
        Stack<String> stk = new Stack<>();

        // now push every separate string into stk
        for (String str : sArr){
            stk.push(str);
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()){
            sb.append(stk.pop()+ " ");
        }
        System.out.println("Reversed words are: "+sb.toString());
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
