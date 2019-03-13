package com.examples.recursive;

/**
 * Created by amit on 27-06-2017.
 */
public class SelectionSort
{
    public static void main(String args[])
    {
        int ary[] = {-1, 0, 5, 3, 1, 0, 4};
        //SelectionSortIteration(ary);
        int[] ssr = SelectionSortRecursion(ary, ary.length);
        for (int index = 0; index < ssr.length; index++) {
            System.out.print(ssr[index] + ", ");
        }


    }

    private static void SelectionSortIteration(int[] ary)
    {
        for (int i = 0; i < ary.length; i++)
        {
            int min_index = i;
            for (int j = i; j < ary.length; j++)
            {
                if(ary[j] < ary[min_index])
                {
                    min_index = j;
                }
            }
            int temp = ary[i];
            ary[i] = ary[min_index];
            ary[min_index] = temp;
            System.out.println("min_index in unsorted list : " + min_index + "");

            for (int index = 0; index < ary.length; index++)
            {
                System.out.print(ary[index] + ", ");
            }
            System.out.println();
        }
    }

    private static int[] SelectionSortRecursion(int[] ary, int n)
    {
        if(n == 0)
        {
            return ary;
        }
        int i = ary.length - n;
        int min_index = i;
        for (int j = i; j < ary.length; j++)
        {
            if(ary[j] < ary[min_index])
            {
                min_index = j;
            }
        }
        int temp = ary[i];
        ary[i] = ary[min_index];
        ary[min_index] = temp;

        return SelectionSortRecursion(ary, n - 1);
    }
}
