package com.exception;

/**
 * Created by zafar.imam on 21-08-2017.
 */

public class ExceptionDemo {
    public static void main(String arg[]){
       String s =  TestTry();
       System.out.println(s);
    }

    private static String TestTry() {
        try{
            System.out.println(10/0);
        }catch (Exception e){
            return "name";
        }
        finally {
            return "finally";
        }
    }
}
