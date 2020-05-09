package com.string;

/**
 * Created by parasmani.sharma on 15/03/2017.
 */

public class ReplaceChar {

    public static void main(String args[])
    {
        String faultString = "New Delhi ( DEL)";

        String correctString = reformedString(faultString);
        System.out.println("Testing..." + correctString);

    }

    private static String reformedString(String faultString) {

        String lineWithoutSpaces = faultString.replaceAll("\\s+","");
        return lineWithoutSpaces;
    }

}
