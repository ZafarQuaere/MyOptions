package com.recursive;

/**
 * Created by amit on 22-06-2017.
 */
public class InsertionSort
{
    public static void main(String[] args)
    {
        int[] ary = {3, 5, 2, 6, -1, -4, 2};
        //int[] ary = {1, 2, 3, 4, 5, 6};
        //int[] ary = {1, 1, 1, 1, 1, 1};
        //int[] ary = {6, 5, 4, 3, 2, 1};
        //ISWhenHavingOneSeparateSortedArray(ary);
        //ISIteration(ary);
        ISRecursion(ary, 1);
    }


    private static void ISRecursion(int[] ary, int i)
    {
        if(i != 0 && i < ary.length)
        {
            int key = ary[i];
            int j = i - 1;

            while (j >= 0 && ary[j] > key)
            {
                ary[j + 1] = ary[j];
                j--;
            }
            ary[j+1] = key;

            //--
            /*for (int index = 0; index < ary.length; index++) {
                System.out.print(ary[index] + ", ");
            }
            System.out.println();*/
            //

            ISRecursion(ary, i + 1);
        }
        else
        {
            for (int index = 0; index < ary.length; index++) {
                System.out.print(ary[index] + ", ");
            }
            System.out.println();
        }
    }

    private static void ISIteration(int[] ary)
    {
        for (int i = 1; i < ary.length; i++)
        {
            int key = ary[i];
            int j = i - 1;

            while (j >= 0 && ary[j] > key)
            {
                ary[j + 1] = ary[j];
                j--;
            }
            ary[j+1] = key;
        }

        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + ", ");
        }
        System.out.println();
    }


    private static void ISWhenHavingOneSeparateSortedArray(int[] ary)
    {
        int[] arySorted = new int[ary.length];
        arySorted[0] = ary[0];
        for (int index = 1; index < ary.length; index++)
        {
            boolean isSorted = false;
            for (int pos = 0; pos < index; pos++)//this one is sorted
            {
                if(arySorted[pos] > ary[index])
                {
                    int value = ary[index];
                    for (int i = pos; i < index + 1; i++)
                    {
                        int temp = arySorted[i];
                        arySorted[i] = value;
                        value = temp;
                    }

                    isSorted = true;
                    break;
                }
            }
            if(!isSorted)
            {
                arySorted[index] = ary[index];
            }
            for (int i = 0; i < arySorted.length; i++) {
                System.out.print(arySorted[i] + ", ");
            }
            System.out.println();
        }
    }
}
