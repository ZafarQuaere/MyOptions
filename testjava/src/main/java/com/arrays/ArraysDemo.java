package com.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zafar.imam on 22-08-2017.
 */

public class ArraysDemo {

    public static void main(String ...arg){

        //compare two char arrays
        compareCharArray();

        hashMapDataInsertion();
    }

    private static void hashMapDataInsertion() {
        Map<String,String> map = new HashMap<>();
        System.out.println(map.put("aa","aa")); //return null
        System.out.println(map.put("bb","bb")); // return null
        System.out.println(map.put("cc","cc")); // return null
        System.out.println(map.put("aa","ee")); // it is duplicate key, so it will return value of previous item of same key (aa).
        System.out.println(map.put("dd","dd")); // return null
    }

    private static void compareCharArray() {
        char [] array1 = new char[]{'r','s','t','u'};
        char [] array2 = new char[]{'r','s','t','u'};

        System.out.println(array1.equals(array2));//it will always return false.

        //arrays class having its own equals method to compare array
        System.out.println(Arrays.equals(array1,array2));
    }
}


