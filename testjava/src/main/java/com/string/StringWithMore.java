package com.string;

/**
 * Created by zafar.imam on 29-11-2016.
 */

public class StringWithMore {
    public static String passengers = "zafar / paras / amit / rao";
    public static void main(String [] args){
//        countSlash(passengers);
        modifyVariableName("this_is_var");
        modifyVariableNametoCPP("thisIsVar");
    }

    private static void modifyVariableNametoCPP(String str) {
        // convert string to char array
        char[] arr = str.toCharArray();
        // take a string with empty value
        String output = "";
        //traverse the array
        for (int i = 0; i < arr.length; i++) {
            //check char is upper case, if yes
            if (Character.isUpperCase(str.charAt(i))) {
                output += "_";
                output += str.charAt(i);
            } else {
                output += str.charAt(i);
            }
        }
        System.out.println("input " +str+ " output "+output.toLowerCase());
    }

    //
    private static void modifyVariableName(String var) {
        // convert string to char array
        char[] charArr = var.toCharArray();
        // take a String val with empty
        String output = "";
        for (int i = 0; i < charArr.length; i++) {

            if (var.charAt(i) == '_') {
                //increase the index to make the character as upper case.
                i++;
                char ch = Character.toUpperCase(var.charAt(i));
                output = output+ch;
            } else
            output = output+charArr[i];
        }
        System.out.println("input " +var+ " output "+output);

    }




    private static void countSlash(String passengers) {

    }
}
