package com.collection.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zafar.imam on 22-08-2017.
 */

public class NCopiesOfObject {

    public static void main(String... arg) {

        printNCopiesOfItems();
        ArrayList list = new ArrayList();

        list.add(2564);
        list.add(698);
        list.add(3);
        list.add(4);
        list.add(658);
        list.add(356);
        list.add(7);


        //get minimum and maximum of collection objects
        System.out.println("Minimum element is : " + Collections.min(list));
        System.out.println("Maximum element is : " + Collections.max(list));

        Collections.sort(list);


        int index = Collections.binarySearch(list, 7);
        System.out.println("binary search : " + index);
        printDataUsingEnumeration(list);

    }

    private static void printDataUsingEnumeration(ArrayList list) {
        Enumeration enumeration = Collections.enumeration(list);
        System.out.println("Collections using enumeration : ");
        while (enumeration.hasMoreElements()) {
            System.out.print(enumeration.nextElement() + " , ");
        }
    }

    private static void printNCopiesOfItems() {
        //Collections class having a static method of nCopies
        //this method returns immutable list containing n copies of specified Objet
        List list = Collections.nCopies(5, "Name");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
