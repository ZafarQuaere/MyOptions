package com.adobe_interview;

import java.util.Arrays;

public class MeanMedianMode {

    public static void main(String[] arg) {
//        int[] arre = {1, 3, 4, 2, 6, 5, 8, 7,9};
        int[] arre = {1, 3, 5, 6, 6, 4, 4,8};
        double mean = findMean(arre);
        System.out.println("Mean of given array is: " + mean);
        System.out.println();
        double median = findMedian(arre);
        System.out.println("Median of given array is: " + median);

        double modeValue = findMode(arre);
        System.out.println("Mode of given array is: " + modeValue);
    }

    /*Mode is the value which occurs most frequently in a set of observations. For example, {6, 3, 9, 6, 6, 5, 9, 3} the Mode is 6,
    as it occurs most often.*/
    private static double findMode(int[] arr) {
        int maxCouunt = 0, maxValue = 0;
        for (int item : arr) {
            int count = 0;
            for (int value : arr) {
                if (value == item)
                    ++count;

            }
            if (count > maxCouunt) {
                maxCouunt = count;
                maxValue = item;
            }
        }
        return maxValue;
    }

    /*MEDIAN
    of a sorted array of size n is defined as the middle element when n is odd and average of middle two elements when n is even.
   when size of array is odd(7) then mean value is 4th element
   when size of array is even(8) then mean is (4th+5th)element/2
   */
    private static double findMedian(int[] arre) {
        double median = 0;
        System.out.println("arr length = " + arre.length );
        int n = arre.length;

        //sorting array
        Arrays.sort(arre);
        if (n%2 == 0){
            median = (arre[(n-1)/2] + arre[(n/2)])/2.0;
        } else {
            median = arre[(arre.length-1)/2];
        }
        return median;
    }

    //MEAN of an array = (sum of all elements) / (number of elements)
    private static double findMean(int[] arre) {
        int noOfElement = arre.length;
        int sumOfElement = 0;
        for (int val : arre)
            sumOfElement = sumOfElement + val;

        System.out.println("Sum = " + sumOfElement + "  NoOfElements = " + noOfElement);
        return (double) sumOfElement / (double) noOfElement;
    }

}
