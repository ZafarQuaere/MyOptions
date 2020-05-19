package com.adobe_interview;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class StringOperations {

    public static void main(String arg[]) {
//        removeDuplicates();
//        reverseString();
//        removeSpaces();
//        findNoOfSubstring();
//        findIndexOfSubstring();
        removeAllInstaceFromString();
        //reverseStringRecusively("Zafar");

    }

    private static String reverseStringRecusively(String str) {

        //base case to handle one char string and empty string
        if (str.length() < 2) {
            return str;
        }

        return reverseStringRecusively(str.substring(1)) + str.charAt(0);

    }

    private static void removeAllInstaceFromString() {
        String s = "One fish, two fish, red fish, blue fish";
        String p = "fish";
        String s1 = s.replaceAll(p, "");
        System.out.println(s1);

        //another way to do it
        String master = "Hello World Baeldung!";
        String target = "Baeldung";
        String replacement = "Java";

        int startIndex = master.indexOf(target);
        int stopIndex = startIndex + target.length();

        StringBuilder builder = new StringBuilder(master);
        System.out.println(builder.toString());

    }

    private static void findIndexOfSubstring() {
//        String str = "JavaExamplesJavaCodeJavaProgram";
//        String subStr = "Java";
        String str = "My Hometown is Gaya, It is Mahatma Budh nagri, popularly called as BodhGaya,My School name is Gaya High School, Gaya";
        String subStr = "Gaya";
        int count = 0, index = 0;
       // while ((index = str.indexOf(subStr,index)) != -1){
        index = str.indexOf(subStr,index);
        while (index >= 0){
            System.out.println("Found at index: "+index);
            index = str.indexOf(subStr,index+1);
            count++;
            //index++;
        }
        System.out.println("Total occurence: "+count);
//        System.out.println("Total occurence: "+(str.split(subStr).length-1));
    }

    /*
    Find number of substring and the indexes of substring from the given large string
     */
    private static void findNoOfSubstring() {
        String bigString = "I am from Gaya, i love the city Gaya, i have a lot of memories in Gaya, which i can never forget";
        String substring = "Gaya";

        char[] bigChar = bigString.toCharArray();
        char[] subChar = substring.toCharArray();
        int i,j,found,count = 0;

//        System.out.println("bigStr= "+bigString.length()+" subStr "+substring.length()+ " bigStr-subStr= "+(bigString.length() - substring.length()));

        for ( i = 0; i < (bigString.length() - substring.length()); i++) {
            found = 1;
            for ( j = 0; j < substring.length(); j++) {
                //if word not matched
                if (bigChar[i+j] != subChar[j]){
                    found = 0;
                    break;
                }
            }
            //if substring is found, print the substring message
            if (found == 1) {
                count++;
                System.out.println(substring+" Found at index: "+i);
            }
        }
        System.out.println("Count: "+count);
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
        System.out.println();

        //here empty space can be removed in one line using built in method
        System.out.println("Before: "+str+"\nAfter: "+str.replaceAll("\\s",""));
    }

    private static void reverseString() {
        String str = "Hello World";

        char[] charArr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            charArr[i] = str.charAt(str.length() -1 -i);
            charArr[str.length()-1-i] = str.charAt(i);
        }

        System.out.println("before reverse: "+str+"\nAfter reverse: "+new String(charArr));
        System.out.println();

        // in one line string can be reverse by using StringBuilder class's built-in reverse method
        System.out.println("Before:"+str+"\nAfter "+new StringBuilder(str).reverse().toString());
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
