package com.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] arg) {
//        kindOfStreams();
//        removeElement();
        userMethodReference();

        useFilter();
    }

    private static void useFilter() {
        List<Integer> list = (Arrays.asList(20,31,30,54,25,66,78));
        System.out.println(list.stream().filter(i -> i%5 == 0).map(integer -> integer*2).reduce(Integer::sum)); //here map is doubling the values
        System.out.println(list.stream().filter(i -> i%5 == 0).reduce(0,(c,e)-> c+e));
    }

    private static void userMethodReference() {
        List<Integer> list = (Arrays.asList(2,3,3,5,2,6,7));
        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//        for (Integer val : list) {
//            System.out.println(val);
//        }
        // here we are using the external iteration to fetch the data from list by using "while" and "for" loops.

        //External iteration (for,while)
        // Internal iteration (streams)

        list.forEach(i -> System.out.println(i));
        System.out.println();
        // OR
       // list.forEach(System.out::println); // it is an example of method reference where println is reference method
        list.forEach(StreamDemo::doubleTheValue); // this is same as above stmt
        System.out.println();
        list.parallelStream().forEach(System.out::println); // here parallelStream() will run in the parallel separate thread


    }
    private static void doubleTheValue(int i){
        System.out.println(i);
    }

    private static void removeElement() {
        //remove 2 from given array
        int arr[ ]={2,3,3,5,2,6,7} ;
        int[] ints = IntStream.range(0, arr.length).filter(i -> i != 0).map(i -> arr[i]).toArray();
        int[] ints1 = IntStream.range(0, ints.length)
                .filter(i -> i != 4) //here it is filtering array escaping position 4
                .map(i -> ints[i]).toArray(); // here it is mapping the filtered output and converting it to an array.
        System.out.println("Before removing 2 "+Arrays.toString(arr));
        System.out.println("After removing 2 "+Arrays.toString(ints1));
    }

    private static void kindOfStreams() {

   /*     Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst() //it will find the first element from stream ie a1
                .ifPresent(System.out::println);  // a1*/

        Stream.of("a1","a2","a3")
                .findAny()
                .ifPresent(System.out::println);
        IntStream.range(1,4).forEach(System.out::println);

        System.out.println();
        IntStream range = IntStream.range(1, 9);
        for (int n : range.boxed().collect(Collectors.toList())) {
            System.out.println(n);
        }

    }
}
