package com.codility;

public class FrogJump {
    // X = beginning, Y = finish, D = distance
    public int solution(int X, int Y, int D) {

        int state = X;
        int count = 0;
        while (state < Y) {
            state = state + D;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int X = 10;
        int Y = 90;
        int D = 30;

    }
}
