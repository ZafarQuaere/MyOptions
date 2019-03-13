package com.test;

/**
 * Created by zafar.imam on 29-07-2017.
 */

public class ProtectedDemo1 {

    protected  int p1 = 10;

    protected void protect1(){
        System.out.println("Protected 1 :");
    }

    public static void main(String arg[]){
        new ProtectedDemo2().protect2();
    }

}
