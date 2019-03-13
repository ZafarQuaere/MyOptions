package com.examples.recursive;

/**
 * Created by amit on 20-06-2017.
 */
public class BinarySearch
{
    public static void main(String args[])
    {
        int ary[] = {0, 12, 23, 34, 45, 56, 67, 78, 89, 90, 100};
        binarySearchRecursive(ary, 34, 0, 10);
        binarySearchIterative(ary, 101, 0, 10);
    }

    //array must be sorted
    private static boolean binarySearchIterative(int[] ary, int x, int left, int right)
    {
        int middle = (left + right)/2;

        while(ary[middle] != x)
        {
            if(left > right)
            {
                System.out.println("Did not find given element in given array.");
                return false;
            }

            if(ary[middle] > x)
            {
                left = left;
                right = middle - 1;
                middle = (left + right)/2;
            }
            else
            {
                left = middle + 1;
                right = right;
                middle = (right + right)/2;
            }
        }

        if(ary[middle] == x)
        {
            System.out.println("Found array at : " + middle);
        }

        return false;
    }

    //array must be sorted
    private static boolean binarySearchRecursive(int[] ary, int x, int left, int right)
    {
        if(left > right)
        {
            System.out.println("Did not find given element in given array.");
            return false;
        }

        int middle = (left + right)/2;

        if(ary[middle] == x)
        {
            System.out.println("Found array at : " + middle);
            return true;
        }
        else if(ary[middle] > x)
        {
            binarySearchRecursive(ary, x, left, middle - 1);
        }
        else
        {
            binarySearchRecursive(ary, x, middle + 1, right);
        }
        return false;
    }
}
