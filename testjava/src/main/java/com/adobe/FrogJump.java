package com.adobe;
/*
There is rever flowing in Jungle, Two Banks A and B.
Frog is standing on Bank A and it wants to go to Bank B.
Stones are kept on the river bed, Stones are kept at 1Unit distance from each other.
Frog can take a jump of 1Unit or 2Unit. On Monsoon some stones inundated in the water.
ex: [0, 1, 1, 0, 1,0,1,1,1]  where - 0=> No Stone, 1=> Stone is present.

Expectation: minimum number of jumps to reach to Bank B and if it is not possible to reach to Bank B, answer should be 1.
 */
public class FrogJump {
    public static void main(String[] arg) {
        //suppose the given array is
//        int[] stoneArray = {0, 1, 1, 0,1, 1,0};
        int[] stoneArray = {1,1,1,1,1,1};
        int noOfJumps = findNumberOfJumps(stoneArray);
        System.out.println("Min jumps is : "+noOfJumps);

    }

    //not all conidtion passing
    //similar case http://buttercola.blogspot.com/2018/10/403-frog-jump.html
    private static int findNumberOfJumps(int[] stoneArray) {
        int source = 0, destination = stoneArray.length+1;
        int count0 =0, count1 = 0;
        // find the consecutive array element;
        for (int i = 0; i < stoneArray.length; i++) {
           if (stoneArray[i] == 0){
               if ((i+1) < stoneArray.length &&  stoneArray[i+1] == 0 ){
                   return -1;
               }
           } else {
               if (i > 0 && stoneArray[i ]== 1 && stoneArray[i-1] == 1 && ( (i+1) <stoneArray.length && (stoneArray[i+1] ==1) )){
                   continue;
                  // count1++;
               } else if (stoneArray[i] == 1){
                   count1++;
               }
           }
        }
        return count1;
    }
}
