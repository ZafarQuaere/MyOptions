package com.adobe_interview.matrix;

public class MatrixSubstraction {
    public static void main(String[] arg) {
        subtract2DArray();
    }

    private static void subtract2DArray() {
        int[][] arr1 = new int[][]{{9,8,7},{6,5,4}};
        int[][] arr2 = new int[][]{{6,5,4},{4,3,2}};

        int rows = arr1.length;
        int column = arr1[0].length;

        int[][] subArr = new int[rows][column];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                subArr[i][j] = arr1[i][j] - arr2[i][j];
            }
        }
        //print subtracted arrar
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(subArr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
