package com.adobe_interview.recusively;

/**
 * Created by amit on 21-10-2016.
 */
public class TOH {
    public static void main(String args[]) {
        solve(3, "A", "B", "C");
    }

    public static void solve(int n, String start, String auxiliary, String end) {
        if (n == 1) {
            System.out.println(start + " -> " + end);
        } else {
            solve(n - 1, start, end, auxiliary);
            System.out.println(start + " -> " + end + " ? ");
            solve(n - 1, auxiliary, start, end);
        }
    }
    //3
    /*
    a > c
    a > b
    c > b
    a > c
    b > a
    b > c
    a > c
    */


    //2
    //a > b
    //a > c
    //b > c

    //1
    //a > c

}
