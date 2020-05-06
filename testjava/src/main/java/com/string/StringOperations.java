package com.string;

import java.util.LinkedHashSet;

public class StringOperations {

    public static void main(String arg[]) {
      //  removeDuplicates();
     //   reverseString();
        removeSpaces();
    }

    private static void removeSpaces() {
        String str = "He ll o Wor ld ";
        char[] cArr = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char value : cArr) {
            if (value == ' ')
                continue;
            builder.append(value);
        }
        System.out.println("Before: "+str+"\nAfter: "+builder.toString());
    }

    private static void reverseString() {
        String str = "Hello World";

        char[] charArr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            charArr[i] = str.charAt(str.length() -1 -i);
            charArr[str.length()-1-i] = str.charAt(i);
        }

        System.out.println("before reverse: "+str+"  After reverse: "+new String(charArr));
    }

    private static void removeDuplicates() {
        String str = "Hellooo Worldw";
        System.out.println("String before removing = " + str);

        char[] mychar = str.toCharArray();
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mychar.length; i++) {
            lhs.add(str.charAt(i));
        }
        for (Character ch : lhs) {
            builder.append(ch);
        }

        System.out.println("String after removing = " + builder.toString());
    }

}
