package com.datastructure;

import java.util.Arrays;

/**
 * Created by zafar.imam on 24-05-2017.
 */

public class SelectionSortDemo {

    public static void main(String[] arg){
        int values [] = {3,6,1,8,4,5};
        System.out.println("Values before sorting :"+ Arrays.toString(values));
        System.out.println();
        int sortedValues[] = sortItems(values);

        System.out.println("Values before sorting :"+ Arrays.toString(sortedValues));
    }

    private static int[] sortItems(int[] values) {// values {3,6,1,8,4,5}
        int i,j,temp,minValue;

        for (i = 0; i<values.length -1;i++){
            minValue = i;
            for (j = i+1; j<values.length; j++){
                if (values[j] < values[minValue]){
                    minValue  = j;
                }
            }
            temp = values[minValue];
            values[minValue] = values[i];
            values[i] = temp;

            System.out.println("Values while sorting :"+ Arrays.toString(values));
        }
        return values;
    }
}
