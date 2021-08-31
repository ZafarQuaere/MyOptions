package com.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.IntStream;

public class ArraysOperation {

    public static void main(String[] arg) {
//        removeDuplicateElement();
//        findMinMax();
//        compareArrayFindIndexOfDiffElement();
        findDuplicateElement();
//        remove2fromElement();
    }

    private static void remove2fromElement() {
        //remove 2 from given array
        int arr[ ]={2,3,3,5,2,6,7} ;
        int[] ints = IntStream.range(0, arr.length).filter(i -> i != 0).map(i -> arr[i]).toArray();
        System.out.println("Removed 2 "+Arrays.toString(ints));
    }

    private static void findDuplicateElement() {
        int[] arr = {10, 20, 20, 30, 30, 40, 50, 50};
        HashSet<Integer> hs = new HashSet<>();
       /* for (int val : arr) {
            if(!hs.add(val)){
                System.out.println(val);
            }
        }*/

        for (int i = 0; i < arr.length; i++) {
            if (!hs.add(arr[i])) {
                System.out.println("Val: " + arr[i] + "  index:" + i);
            }
        }

        //Arrays.stream(arr).filter(i -> !hs.add(i)).forEach(System.out::println);
    }

    private static void compareArrayFindIndexOfDiffElement() {
        int[] arr1 = {3, 5, 2, 5, 2};
        int[] arr2 = {2, 3, 5, 5, 2};
        // int[] indexArr = compareElements(arr1,arr2);
        String indexArr = compareElements(arr1, arr2);
        // System.out.println("Index of different elements : "+Arrays.toString(indexArr));
        System.out.println("Index of different elements : " + Arrays.asList(indexArr));

    }

    private static String compareElements(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return null;
        }
//        Arrays.sort(arr1);
//        Arrays.sort(arr2);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                builder.append(i + " ");

        }
        System.out.println(Arrays.equals(arr1, arr2)); // this will return, arrays are equal or not.

        return builder.toString();
    }

    private static void findMinMax() {
        //find the maximum and minimum value from array
        int[] arr = {10, 8, 20, 5, 30, 40, 10, 25};

        // you can find min max using just two line as below
//        Arrays.sort(arr);
//        System.out.println("Min Value : "+arr[0]+" Max Val: "+arr[arr.length-1]);
        int min = arr[0];
        int max = arr[0];
        for (int value : arr) {
            if (min > value)
                min = value;

            if (max < value)
                max = value;
        }
        System.out.println("Min : " + min + " max: " + max);
    }

    private static void removeDuplicateElement() {
        int[] arr = {10, 20, 20, 30, 30, 40, 50, 50};
        //  LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
        HashSet<Integer> lhs = new HashSet<>();
        for (int val : arr) {
            lhs.add(val);
        }
        Integer[] temp = new Integer[lhs.size()];
        lhs.toArray(temp);
        System.out.println("After removing duplicate element: " + Arrays.toString(temp));
    }
}
