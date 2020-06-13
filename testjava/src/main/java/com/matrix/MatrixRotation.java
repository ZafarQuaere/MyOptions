package com.matrix;

import java.util.Scanner;

//https://youtu.be/nkzBlvypQmI
public class MatrixRotation {
    public static void main(String[] arg){

        int n = 3;// matrix with 3*3
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter value of n ");
//        n = scanner.nextInt();
        System.out.println("N = "+n);
        int[][] arr1 = new int[][]{{ 1,2,3,4 },{5,6,7,8}};
        int[][] arr = new int[][]{{ 1,2 },{5,6}};
        rotate2DArray(arr);


    }

    private static void rotate2DArray(int[][] arr) {
        int row = arr.length;
        int column = arr[0].length;
        System.out.println("Row : "+row+" Column : "+column);

        for (int i = 0; i < column ; i++) {
            for (int j = row-1 ; j >= 0 ; j--) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

    }
}
