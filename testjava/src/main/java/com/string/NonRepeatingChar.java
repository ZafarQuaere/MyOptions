package com.string;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

//How to find the first non-repeated character of a given String?
public class NonRepeatingChar {

    private static final int NO_OF_CHARS = 256;
    private static char[] count = new char[NO_OF_CHARS];

    public static void main(String[] arg) {
        //find first non repeating character from string
//        findNonRepeating("geeksforgeeks");
        findFirstNonRepeating("geeksforgeeks");
//       findNonRepeatingUsingSet("geeksforgeeks");

    }

    private static void findNonRepeatingUsingSet(String str) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < str.length(); i++) {
            if (set.add(str.charAt(i))){
                System.out.println(str.charAt(i));
            }
        }
    }

    private static void findNonRepeating(String str) {
        //convert the string into char array
        //find index of first non repeating character by comparing chars.
        findCharCount(str);
        int index = -1, i;
        for (i = 0; i < str.length(); i++) {
            if (count[str.charAt(i)] == 1 ){
                index = i;
                break;
            }
        }
        System.out.println(index == -1 ? "Either all characters are repeating or string is empty"
                        : "First non-repeating character is " + str.charAt(index));
    }

    private static void findCharCount(String str) {
        for (int i = 0; i < str.length(); i++)
            count[str.charAt(i)]++;
    }

    // second way to get non repeated char
    private static void findFirstNonRepeating(String str) {
        for (int i = 0; i < str.length(); i++) {
            boolean unique = true;
            for (int j = 0; j < str.length(); j++) {
                if (i != j && str.charAt(i) == str.charAt(j)){
                    unique = false;
                    break;
                }
            }
            if (unique) {
                System.out.println("first non repeating char is : " + str.charAt(i));
                break;
            }
        }
    }

    // third way to get non repeated char
    public static char getFirstNonRepeatedChar(String str) {
        Map<Character,Integer> counts = new LinkedHashMap<>(str.length());

        for (char c : str.toCharArray()) {
            counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
        }

        for (Map.Entry<Character,Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("didn't find any non repeated Character");
    }

}
