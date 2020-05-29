package com.java8.lambda;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaDemo2 {
    public static void main(String[] arg) {
        List<String> list = Arrays.asList("abc","def","ghi","jkl","dmn");
        Predicate<String> data = s -> s.startsWith("d");
        boolean abc = data.test("def");// if matches with above condition it will return true else false.
        System.out.println(abc);
        boolean aaa = data.test("aaa"); // it will return false as the string does not starts with 'd' as predicate applied.
        System.out.println(aaa);
        for (String value : list) {
            if (data.test(value)) {
                System.out.println(value);
            }
        }

        intefacesOfFunctional();
    }

    private static void intefacesOfFunctional() {
        //Consumer
        //Supplier
        //Predicate
        //Functions

        //consumers
        Consumer<String> consumer = LambdaDemo2::displayNames;
        consumer.accept("name1");
        consumer.accept("name2");
        consumer.accept("name3");
        consumer.accept("name4");

        //supplier
        List<String> names = Arrays.asList("abc","def","ghi","jkl","dmn");
        names.stream().forEach((name) -> displayName2(() -> name));

        //predicate
        Predicate<String> data = (s -> s.startsWith("d"));
        for (String value : names) {
            if (data.test(value)) {
                System.out.println(value);
            }
        }

        //Function
        Function<Integer,Double> functionTest = (testData) -> testData/2.0;
        System.out.println(functionTest.apply(100));
    }

    private static void displayNames(String name) {
        System.out.println(name);
    }

    private static void displayName2(Supplier<String> name){
        System.out.println(name.get());
    }
}

interface sampleFncnlInterface {

    public void execute();

    public default void print(String text) {
        System.out.println(text);
    }

    public static void print(String text, PrintWriter printWriter) {
        printWriter.write(text);
    }

    sampleFncnlInterface interf = () -> {
        System.out.println("executing...");
    };


}