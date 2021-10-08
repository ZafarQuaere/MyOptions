package com.arrays;


import java.lang.reflect.Array;
import java.util.Arrays;

/*
The girl keeps track of goals she scores in each game
WAP

 */
public class TTNDemo {
   static int[] noOfGoals = {2,3,6,3,4,1,2};
   static int bestCount = 0; //
   static int worstCount = 0;

    public static void main(String[] arg){
        int[] theRecords = findTheRecords(noOfGoals);
        System.out.println("Best and worst record count :"+ Arrays.toString(theRecords));
    }

    private static int[] findTheRecords(int[] noOfGoals) {
        int bestScore = noOfGoals[0];
        int worstScore = noOfGoals[0];
        for (int i = 0; i < noOfGoals.length; i++) {
            for (int j = i+1; j < noOfGoals.length; j++) {
                if (noOfGoals[j]> bestScore) {
                    bestScore = noOfGoals[j];
                    bestCount++;
                }
                if (noOfGoals[j] < worstScore){
                    worstScore = noOfGoals[j];
                    worstCount++;
                }
            }
        }
        int[] intances = new int[2];
        intances[0] = bestCount;
        intances[1] = worstCount;
        return intances;
    }

}
