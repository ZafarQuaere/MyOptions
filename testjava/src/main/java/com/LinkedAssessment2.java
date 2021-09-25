package com;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinkedAssessment2 {
    public static void main(String[] arg) {
        System.out.println("apple".compareTo("banana")); // returns a positive integer

        String[] array = {"abc", "2", "10", "0"};
        List<String> list = Arrays.asList(array);
        Collections.sort(list);
        System.out.println(Arrays.toString(array));
//        manipulation();
    }

    private static void manipulation() {
        /*int a = 1;
        int b = 0;
        int c = a/b;
        System.out.println(c);*/
        String message = "Hello world!";
        String newMessage = message.substring(6, 12)
                + message.substring(12, 6);
        System.out.println(newMessage);
    }
}
