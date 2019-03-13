package com.examples.recursive;

/**
 * Created by amit on 20-06-2017.
 */
public class LinearSearch
{
    public static void main(String args[]) {
        int ary[] = {0, 12, 23, 34, 45, 56, 67, 78, 89, 90, 100};

        System.out.println("found at : " + linearSearch(ary, 012));
    }

    private static int linearSearch(int[] ary, int x)
    {
        for (int index = 0; index < ary.length; index++) {
            if(ary[index] == x)
            {
                return index;
            }
        }

        return -1;
    }
}
