package com.string;

import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Created by zafar.imam on 02-08-2017.
 */

public class RemoveDuplicates {

    public static void main(String arg[]){
        System.out.println("Enter any String ");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        StringBuilder removedDuplicate = removeDuplicateString(string);
        System.out.println("Removed Duplicates : "+removedDuplicate);
    }

    private static StringBuilder removeDuplicateString(String string) {
        char arr[] = string.toCharArray();
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            lhs.add(string.charAt(i));
        }
        for (Character ch: lhs) {
            builder = builder.append(ch);
        }

        return builder;
    }
}
