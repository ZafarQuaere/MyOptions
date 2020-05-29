package com.matrix;

public class MatrixMultification {
    public static void main(String[] arr) {
        multiply1DArray();
        multiply2DArray();
    }

    private static void multiply1DArray() {
        int[] arr1 = new int[]{9,8,7,6,5,4};
        int[] arr2 = new int[]{6,5,4,4,3,2};
        int[] mulArr = new int[arr1.length];

        for (int i = 0; i <arr1.length; i++) {
            mulArr[i] = arr1[i] * arr2[i] ;
        }

        //print array
        for (int i : mulArr) {
            System.out.print(i+" ");
        }
    }

    private static void multiply2DArray() {
        int[][] arr1 = new int[][]{{9,8,7},{6,5,4}};
        int[][] arr2 = new int[][]{{6,5,4},{4,3,2}};

        int rows = arr1.length;
        int columns = arr1[0].length;

        int[][] mul = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mul[i][j] = arr1[i][j] * arr2[i][j];
            }
        }

        //print the multiplied array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(mul[i][j]+" ");
            }
            System.out.println();
        }
    }
}
