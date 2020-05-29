package com.matrix;


import java.util.Arrays;

public class MatrixAddition {

    public static void main(String[] arg) {
        //One dimensional array
       // add1DArray();

        //Two dimensional array
//        add2DArray();

        //add 3 dimensional array
        initialize3DArray();
//        add3DArray();
    }

    private static void initialize3DArray() {
        //3d array initialize
        int[][][] arr3d = new int[4][4][4];
        //value added to 3d array
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    arr3d[i][j][k] = i+j+k+1;
                }
            }
        }

        //print the value of 3d array
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(" "+arr3d[i][j][k]);
                }
            }
        }

        /*
        1 2 3 4     2 3 4 5     3 4 5 6     4 5 6 7
        2 3 4 5     3 4 5 6     4 5 6 7     5 6 7 8
        3 4 5 6     4 5 6 7     5 6 7 8     6 7 8 9
        4 5 6 7     5 6 7 8     6 7 8 9     7 8 9 10
         */
    }

    private static void add3DArray() {
        int[][][] arr1 = new int[][][]{
                {
                        {1, 2, 3, 4}, {5, 6, 7, 8}
                }, {
                {2, 2, 2, 2}, {4, 4, 4, 4,}}
        };
        int[][][]arr2 = new int[][][]{
                {
                        { 5,6,7,8 },{1,2,3,4}
                }, {
                { 3,3,3,4 },{2,2,2,3}
        }
        };

       int rows = arr1.length;
       int columns = arr1[0].length;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(" "+arr1[i][j][k]);
                }
            }
        }

    }

    private static void add1DArray() {
        int[] arr1 = new int[] { 1,2,3,4};
        int[] arr2 = new int[] { 4,3,2,1};
        int[] arr3 = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr3[i] = arr1[i] + arr2[i];
        }

        System.out.println("Print 3rd Array: "+ Arrays.toString(arr3));
    }

    private static void add2DArray() {
        int[][] arr1 = new int[][]{{ 1,2,3,4 },{5,6,7,8}};
        int[][] arr2 = new int[][]{{ 5,6,7,8 },{1,2,3,4}};
        int rows = arr1.length;
        int column = arr1[0].length;

        int[][] arr3 = new int[rows][column];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                arr3[i][j] = arr1[i][j]+arr2[i][j];
            }
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < column; j++){
                System.out.print(arr3[i][j] + " ");
            }
            System.out.println();
        }


       // System.out.println("Addition of 2D array: "+Arrays.toString(arr3));
      /*  int[][][] arr= new int[][][]{
                {
                        {1,2,3},{4,5,6}
                },
                {
                        {7,8,9},{10,11,12}
                },
                {
                        {13,14,15},{16,17,18}
                },
                {
                        {19,20,21},{22,23,24}
                }
        };*/
    }
}
