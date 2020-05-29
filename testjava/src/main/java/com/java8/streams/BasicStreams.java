package com.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicStreams {
    public static void main(String[] arg) {

//        squareOfNumbers();
//        lowerCaseStreams();

        concurrentStream();

    }

    private static void squareOfNumbers() {
        List<Integer> list = Arrays.asList(2,3,4,5,6,5,7);
        List<Integer> squareList = list.stream().map(i -> i * i)
                .collect(Collectors.toList());

        Set<Integer> squareSet = list.stream().map(i -> i*i)
                .collect(Collectors.toSet());
        System.out.println(squareList);
        System.out.println(squareSet);

        //now find even number from list
        List<Integer> evenList = squareList.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println("evenList "+evenList);

        //Adding all the even numbers
        Integer evenNumber = squareList.stream().filter(i -> i % 2 == 0).reduce(0, (out, i) -> out + i);
        System.out.println("evenNumber "+evenNumber);

    }


    private static void lowerCaseStreams() {
        List<String> strList = new ArrayList<>();
        strList.add("JANUARY");
        strList.add("FEBRUARY");
        strList.add("MARCH");
        strList.add("APRIL");
        strList.add("MAY");

//        long count = strList.stream().map(value -> value.toLowerCase()).count();
//        System.out.println(count);
        List<String> collect = strList.stream().map(value -> value.toLowerCase()).collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void concurrentStream() {
        List<Integer> list = Arrays.asList(2,3,4,5,6,5,7);

        Stream.of(1,2,3,4,5,6,7)
                .forEach(System.out::println);
        System.out.println();
        // Parallel Stream
        Stream.of('a','s','d','f','j','k','l')
                .parallel().forEach(System.out::println); // here it will not print in a particular sequence due to parallel process

        Integer sumOfEven = list.stream().parallel().filter(i -> i % 2 == 0).reduce(0, (i, j) -> i + j);
        System.out.println(sumOfEven);

        list.forEach(numbers -> {
            System.out.println(numbers);
        });
    }

}
