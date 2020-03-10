package com.collection.List;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by amit on 29-08-2017.
 */
public class LinkedListTest {
    public static void main(String args[]) {
        LinkedList<String> list = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            //list.add("" + i);
        }

        /*for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/


        list.addFirst("f1");
        list.addFirst("f2");

        list.addLast("l1");
        list.addLast("l2");


        Object[] ary = list.toArray();


        System.out.println(list);

        ListIterator<String> iterator = list.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
            iterator.previous();
            System.out.println("inside");
        }

        System.out.println(list);

    }
}
