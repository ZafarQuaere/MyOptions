package com.string;

public class FindLongestPrefix {

    public static void main(String[] arg) {
        String[] values = new String[] {"flower","flow","flight"};
        findTheLongestPrefix(values);
    }

    private static void findTheLongestPrefix(String[] values) {
        String str1 = values[0];

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i)== values[1].charAt(i)){
                output.append(str1.charAt(i));
            }
        }
        System.out.println("Output is :"+output);
    }
}
