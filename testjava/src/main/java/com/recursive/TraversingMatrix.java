package com.recursive;

import java.util.Random;

/**
 * Created by amit on 24-10-2016.
 */
public class TraversingMatrix
{
    public static void main(String args[])
    {
        //97-122 lowercase char ascii
        char matrix[][] = new char[5][5];
        for (int index = 0; index < matrix.length; index++) {
            for (int pos = 0; pos < matrix[index].length; pos++) {
                matrix[index][pos] = (char) (new Random().nextInt(122 - 97 + 1) + 97);
                System.out.print(" " + matrix[index][pos]);
            }
            System.out.println();
        }
        System.out.println("----");


        //traverseLinear(matrix, 0, 0);
        traverseSpiral(matrix, 0, 0);
    }

    private static void traverseLinear(char[][] matrix, int col, int row)
    {
        if(row >= matrix[0].length || col >= matrix.length)
        {
            return;
        }

        System.out.println("at : " + col + " " + row + " > " + matrix[col][row]);

        traverseLinear(matrix, col, row + 1);
        if(row + 1 == matrix.length - 1)
        {
            traverseLinear(matrix, col + 1, 0);
        }
    }

    private static void traverseSpiral(char[][] matrix, int col, int row)
    {
        if(row >= matrix[0].length || col >= matrix.length)
        {
            return;
        }

        System.out.println("at : " + col + " " + row + " > " + matrix[col][row]);



        if(row == matrix.length - 1)
        {
            traverseSpiral(matrix, col + 1, row);
        }
        else
        {
            traverseSpiral(matrix, col, row + 1);
        }

        
    }
}
