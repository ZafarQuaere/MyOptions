package com.arrays;

import java.util.HashMap;
import java.util.Map;
/* Program for finding out majority element in an array */
public class MajorityElement {

    public static void main(String[] arg) {
        int[] input = {3, 3, 4, 2, 3, 3, 2, 4, 4};
        findMajority(input);
    }

    private static int findMajority(int[] input) {
        if (input.length <= 0)
            return 0;
        // first create a map and put the element in the map
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int j : input) {
            // check if the value is already in the map, if exists then increase the counter and put in the map
            if (map.containsKey(j)) {
                int count = map.get(j) + 1;
                if (count > input.length / 2) { // check if counter is greater than array length
                    System.out.println("Majority found : " + j);
                } else
                    map.put(j, count);
            } else // when element is not in the map, put in the map.
                map.put(j, 1);
        }
        System.out.println("No majority element found: ");
        return 0;
    }
}
