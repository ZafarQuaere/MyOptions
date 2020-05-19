package com.java8.lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaDemo {
    public static void main(String[] arg) {

        //Syntax of lambda expression
        //( paramlist ) -> { method body };


        //Ordinary way to call interface
        LambdaMessage lambdaMessage = new LambdaMessage() {
            @Override
            public String message(String message) { return "HI"; }
        };

        //Lambda way to call interface
        LambdaMessage lm = message -> message+" Hi Stupid ";
        System.out.println(lm.message("Lambda bola!") );

        lambdaExpressions();
        lambdaWithList();

    }

    private static void lambdaWithList() {
        List<String> list=new ArrayList<String>();
        list.add("Rick");
        list.add("Negan");
        list.add("Daryl");
        list.add("Glenn");
        list.add("Carl");
        list.forEach(names -> System.out.println(names));
        System.out.println();
        list.forEach(System.out::println);
    }

    private static void lambdaExpressions() {
        // lambda expression with single parameter num
        LambdaSingleExpression lse = (num) -> num+5;
        System.out.println(lse.incrementNo(5));

        // lambda expression with multiple arguments
        LambdaMultiParam lMp = (s1,s2) -> s1+s2;
        System.out.println(lMp.concatinate("Zafar ","Imam"));
    }
}
interface LambdaMessage {
    public String message(String message);
}
interface LambdaSingleExpression{
    public int incrementNo(int n);
}
interface LambdaMultiParam{
    public String concatinate(String s1,String s2);
}