package com.examples.recursive;

/**
 * Created by amit on 21-06-2017.
 */
public class BubbleSort
{
    public static void main(String args[])
    {
        int arr[] = {11, 1, 13, 111, 55};
        bubbleSort(arr);
        //bubbleSortRecursive(arr);
    }

    private static void bubbleSort(int[] ary)
    {
        for (int i = 0; i < ary.length - 1; i++)
        {
            for (int index = 0; index < ary.length - i - 1; index++)
            {
                if(ary[index] > ary[index + 1])
                {
                    int temp = ary[index];
                    ary[index] = ary[index + 1];
                    ary[index + 1] = temp;
                }
            }

            for (int index = 0; index < ary.length; index++) {
                System.out.print(ary[index] + ", ");
            }

            System.out.println();
        }
    }


    private static void bubbleSortRecursive(int[] ary)
    {
        int[] buffer = new int[ary.length - 1];
        for (int index = 0; index < ary.length - 1; index++)
        {
            if(ary[index] > ary[index + 1])
            {
                int temp = ary[index];
                ary[index] = ary[index + 1];
                ary[index + 1] = temp;
            }
            buffer[index] = ary[index];
        }
        if(buffer.length != 0)
        {
            bubbleSortRecursive(buffer);
        }

        for (int index = 0; index < ary.length; index++) {
            System.out.print(ary[index] + ", ");
        }
        System.out.println();
    }
}
