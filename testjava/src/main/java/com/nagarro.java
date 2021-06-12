package com;

import java.util.HashMap;
import java.util.Map;

public class nagarro {
    public static void main(String[] arg) {
        // find the majority element from array
        findMajority();
    }

    private static void findMajority() {
        int[] input = {3, 4, 4, 2, 3, 4, 2, 4, 4};
        Map<Integer,Integer> map = new HashMap<>();
        for (int j : input) {
            if (map.containsKey(j)) {
                int count = map.get(j) + 1;
                if (count > input.length / 2) {
                    System.out.println("majority of element found: " + j);
                    return;
                } else
                    map.put(j, map.get(j) + 1);
            } else {
                map.put(j, 1);
            }
        }
        System.out.println("no majority element found: ");
    }
}
