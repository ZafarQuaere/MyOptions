package com.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LambdaDemo {
    public static void main(String[] arg) {

        //Syntax of lambda expression
        //( paramlist ) -> { method body };


        //Ordinary way to initiate interface
        LambdaMessage lambdaMessage = new LambdaMessage() {
            @Override
            public String message(String message) { return "HI"; }
        };

        //Lambda way to initiate interface
        LambdaMessage lm = message -> message+" Hi Stupid ";
        System.out.println(lm.message("Lambda bola!") );

        lambdaExpressions();
        lambdaWithList();
        lambdaWithFilter();

    }

    private static void lambdaWithFilter() {
        List<Product> list=new ArrayList<Product>();
        list.add(new Product(1,"Samsung A5",17000f));
        list.add(new Product(3,"Iphone 6S",65000f));
        list.add(new Product(2,"Sony Xperia",25000f));
        list.add(new Product(4,"Nokia Lumia",15000f));
        list.add(new Product(5,"Redmi4 ",26000f));
        list.add(new Product(6,"Lenevo Vibe",19000f));

        //using lambda to filter data
        Stream<Product> filteredData = list.stream().filter(p -> p.price > 2000);

        //using lambda to iterate through collections
        filteredData.forEach(product -> System.out.println(product.name+" : "+product.price));
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
class Product{
    int id;
    String name;
    float price;
    public Product(int id, String name, float price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }
}