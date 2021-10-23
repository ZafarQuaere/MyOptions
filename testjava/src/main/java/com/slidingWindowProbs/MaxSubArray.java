package com.slidingWindowProbs;
/*
Maximum sub Array of Size k
Given an array of positive numbers and a positive number k
Find the maximum sum of any contiguous sub array of size k
example:
 input: [2,1,5,1,3,2], k = 3.
 output: 9 (sum of 5,1,3)
 */
public class MaxSubArray {
    public static void main(String[] arg) {
        int[] arr = {2,1,5,1,3,2};
        int k = 3;
        findTheMaxSumOfSubArr(arr,k);
    }

    private static void findTheMaxSumOfSubArr(int[] arr, int k) {
        if (arr.length == 0 || k <= 0) {
            return;
        }
        int sum = 0;
        int j; // keeping it outside to use in next for loop.
        
        // finding the sum of first k element
        for (j = 0; j < k; j++) {
            sum = sum + arr[j];
        }
        int maxSum = sum; // initial max sum
        int start = 0;
        for (int i = j; i < arr.length; i++) {
            sum = sum + arr[i]- arr[start];
            start++;
            if (sum > maxSum){
                maxSum = sum;
            }
        }
        System.out.println("Max sum of contiguous subArray: "+maxSum);
    }
}
