package com.common;

/**
 * Created by zafar.imam on 21-09-2017.
 */

public class SingletonTest {
    public static void main(String args[]) {
        Normal normal1 = new Normal();
        Normal normal2 = new Normal();


    }
}

class Normal {

}

class SingleTon {
    private static SingleTon s = new SingleTon();

    private SingleTon() {

    }

    public static SingleTon getInstance() {
        return s;
    }
}

