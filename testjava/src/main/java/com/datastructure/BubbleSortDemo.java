package com.datastructure;

import java.util.Arrays;

/**
 * Created by zafar.imam on 23-05-2017.
 */

public class BubbleSortDemo {

    public static void main(String arg[]){

        int values[] = {9,5,6,8,4,7};
        System.out.println("Values before sorting "+ Arrays.toString(values));
        System.out.println("Lenght of Array "+ values.length);
        doSorting1(values);
    }

    private static void doSorting(int[] values) {
        int i,j,temp;
        for ( i = 0; i < values.length; i++) {

            for ( j = 0; j < values.length - i - 1; j++) {


                if( values[j] > values[j+1])
                {
                    temp = values[j];
                    values[j] = values[j+1];
                    values[j+1] = temp;

                }
            }

        }
        System.out.println("Values After sorting "+ Arrays.toString(values));

    }

    private static void doSorting1(int[] values) {
        int holder ;
        for (int i = 0; i < values.length; i++) {

            boolean flag = false;
            for (int j=0; j<values.length-i-1;j++){

                if (values[j]> values[j+1]){
                    holder = values[j];
                    values[j] = values[j+1];
                    values[j+1] = holder;
                    System.out.println(" sorting "+ Arrays.toString(values));
                }
                flag = true;
            }
            if (!flag){
                break;
            }

        }

        System.out.println("Values After sorting "+ Arrays.toString(values));
    }


}
