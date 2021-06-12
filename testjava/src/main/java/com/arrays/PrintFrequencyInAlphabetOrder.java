package com.arrays;

// Java implementation to
// print the characters and
// frequencies in order
// of its occurrence

import java.util.HashMap;
import java.util.Map;

public class PrintFrequencyInAlphabetOrder {
    public static void main(String[] arg) {
        String val = "geeksforgeeks";
        findAndPrintFrequency(val);
//        prCharWithFreq(val);
    }

    private static void findAndPrintFrequency(String str) {
        //store all character in the map first
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))){
                map.put(str.charAt(i),map.get(str.charAt(i))+1);
            } else
                map.put(str.charAt(i),1);
        }

        // now retrieve the character alphabetical order
        // Print characters and their
        // frequencies in same order
        // of their appearance
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) != 0){
                System.out.print(str.charAt(i));
                System.out.print(map.get(str.charAt(i))+" ");
                map.put(str.charAt(i),0);
            }
        }
    }

    public static void prCharWithFreq(String s) {

        // Store all characters and
        // their frequencies in dictionary
        Map<Character, Integer> d = new HashMap<Character, Integer>();


        for (int i = 0; i < s.length(); i++) {
            if (d.containsKey(s.charAt(i))) {
                d.put(s.charAt(i), d.get(s.charAt(i)) + 1);
            } else {
                d.put(s.charAt(i), 1);
            }
        }

        // Print characters and their
        // frequencies in same order
        // of their appearance
        for (int i = 0; i < s.length(); i++) {

            // Print only if this
            // character is not printed
            // before
            if (d.get(s.charAt(i)) != 0) {
                System.out.print(s.charAt(i));
                System.out.print(d.get(s.charAt(i)) + " ");
                d.put(s.charAt(i), 0);
            }
        }
    }

}
