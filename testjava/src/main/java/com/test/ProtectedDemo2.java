package com.test;

/**
 * Created by zafar.imam on 29-07-2017.
 */

public class ProtectedDemo2 {
    protected  int p2 = 20;

    protected void protect2(){
        System.out.println("Protected 2 ");

    }

    public static void main(String arg[]){
        ProtectedDemo1 pro1 = new ProtectedDemo1();
        pro1.protect1();
        System.out.println(pro1.p1);
    }
}
