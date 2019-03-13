package com.datastructure;

import java.util.Arrays;

/**
 * Created by zafar.imam on 23-05-2017.
 */

public class InsertionSortDemo {

    public static void main(String arg[]){
        int values [] = {9,1,6,2,4,3};
        System.out.println("Values before Sorting : "+ Arrays.toString(values));
        System.out.println();
      int sorted[] = applyInsertionSort1(values);

        System.out.println("Values after Sorting : "+Arrays.toString(sorted));

    }

    /**
     * Insertion sort basically compare with its left element and swap with it.
     * all the elements use the same comaprison with it left element until elements left is
     * smaller.
     */
    private static int[] applyInsertionSort1(int[] values) {
        int i,j,min;

        for (i = 1; i <values.length; i++ ){// values [9, 1, 6, 2, 4, 3]
            min = values[i];
            j = i-1;
            while (j >= 0 && min < values[j] ){
                values[j+1] = values[j];
                j--;
            }

            values[j+1] = min;
        }

        return  values;
    }

    private static void applyInsertionSort(int[] values) {//values{5,1,6,2,4,3}
        int i,j,key;

        for ( i = 1; i<values.length;i++ ){
            key = values[i];//1
            j = i-1;        //j =0;

            //i have to compare every element to key
            while (j >= 0 && key < values[j]){

                values[j+1] = values[j];//
                j--;
              //  System.out.println("Values during Sorting : "+ Arrays.toString(values));
            }
            values[j+1] = key;

        }
        System.out.println();
        System.out.println("Values After Sorting : "+ Arrays.toString(values));

    }
}
