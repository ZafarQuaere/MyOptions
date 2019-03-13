package com.string;

/**
 * Created by zafar.imam on 10-10-2017.
 */

public class StringMethodsDemo {
    public static void main(String ...arg){
        String string = "GeeksForGeek";
        //or String string = new String("GeeksForGeek");

        System.out.println("Length of String : "+string.length());

        //returns the character at ith index
        System.out.println("Character at 5th position : "+string.charAt(5));

        //return the substring from ith index to end of String
        System.out.println("Substring from 3rd to end of String : "+string.substring(3));

        //return the substring form i to j-1 index
        System.out.println("Substring from 3rd to j-1 String  : "+string.substring(3,9));


        //Concatenates string2 to end of string1
        String s1 = "Zafar ";
        String s2 = "Imam ";
        System.out.println("Concatenated String : "+s1.concat(s2));


        //returns the index of first occurence of specified words form string
        String s3  = "Learn Share Learn ";
        System.out.println("Index of Share : "+s3.indexOf("Share"));

        //return the index of first occurenece of String from starting to some spedified string.
       // System.out.println("Index of a = "+s3.substring('a',3));

        //converting cases
        String word  =  "GeekYme";
        System.out.println("converting to lower : "+word.toLowerCase());

        System.out.println("converting to higer : "+word.toUpperCase());


        //StringBuffer has some imp methods such as reverse(),delete(),deleteCharAt() ,replace() etc
        callStringBufferMethods(string);

    }

    private static void callStringBufferMethods(String string) {
        System.out.println();
        System.out.println();
        StringBuffer buffer = new StringBuffer(string);

        //reverse String
        System.out.println("Reverse of : "+string+"   is : "+buffer.reverse());

        //delete character between i and j
        System.out.println("Delete Char between 2 to 5 from "+buffer+" "+buffer.delete(2,5));

        //delete character from a specific index of String .
        System.out.println("Delete Character at 6 of "+buffer+" : "+buffer.deleteCharAt(6));
    }
}
