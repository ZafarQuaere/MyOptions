package com.practice;

import java.io.IOException;
import java.util.Scanner;

public class Practice {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter any value ");
        Scanner scanner = new Scanner(System.in);
        int value = 4;
        if (scanner.hasNextInt()){
            value = scanner.nextInt();
        }
        Practice demo = new Practice();
        demo.squareNo(value);
    }

    public void squareNo(int value) throws IOException {
        System.out.println(value*value);
    }

    
}
