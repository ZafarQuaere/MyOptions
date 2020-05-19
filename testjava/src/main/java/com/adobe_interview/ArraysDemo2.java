package com.adobe_interview;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArraysDemo2 {
    public static void main(String[] arg) {
        int[] arr = {2, 3, 3, 5, 2, 6, 7};

        //remove 2 from above array
        removeElement(arr);

    }

    private static void removeElement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        System.out.println("Before removing 2 from array: " + Arrays.toString(arr));
        int[] remArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2 ){
                continue;
            }
            remArr[i] = arr[i];
        }
        System.out.println("After removing 2 from array : "+Arrays.toString(remArr));
        ///////////////////////////OR //////////////////////
//        int[] ints = IntStream.range(0, arr.length).filter(i -> i != 4).map(i -> arr[i]).toArray();
//        System.out.println("After removing 2 from array : " + Arrays.toString(ints));


       /* Integer[] myArr = new Integer[]{2, 3, 3, 5, 2, 6, 7};
        List<Integer> list = Arrays.asList(myArr);
        list.remove(0);*/

    }
}
