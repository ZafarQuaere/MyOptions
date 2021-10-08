package com;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HarmanDemo {

    static int[] arr = {2,7,11,15};
    static int target = 10;

    public static void main(String[] arg) {
//        int[] indexes = indexOfSum(arr, target);
//        System.out.println("Indexes are:"+ Arrays.toString(indexes));

        int[] ints1 = sumOfTwoNum(arr, target);
        System.out.println("Indexes of sum of array: "+Arrays.toString(ints1));
        int[] ints = twoSumUsingMap(arr, target);
        System.out.println("Array indexes: "+Arrays.toString(ints));
    }

    private static int[] twoSumUsingMap(int[] arr, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] indArr = new int[2];
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i],i);
        }

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
//                return new int[] {i,map.get(complement)};
                indArr = new int[]{i, map.get(complement)};
            }
        }
       return indArr;
    }

    private static int[] sumOfTwoNum(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i]+arr[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No sum of two elements is equal to target");
    }

    private static int[] indexOfSum(int[] arr, int target) {
        int[] indexes = new int[2];
        int start = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (start+arr[i] == target) {
                indexes[0] = 0;
                indexes[1] = i;
            }
        }
        return indexes;
    }
}
