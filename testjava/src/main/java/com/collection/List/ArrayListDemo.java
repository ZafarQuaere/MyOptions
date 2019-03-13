package com.collection.List;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Created by amit on 20-06-2017.
 */
public class ArrayListDemo {

    public static void main(String args[]) {
        ArrayList arrayList = new ArrayList();
        System.out.println(arrayList.add("abc"));
        arrayList.add("def");
        arrayList.add("ghi");
        arrayList.add(1);
        arrayList.add(2);


       // printArrayListData(arrayList);

        //add element at specific index as
      //  arrayList.add(3,"Inserted");
        System.out.println();
        //printArrayListData(arrayList);

        //Creating other collection object VEctor
        Vector vector = new Vector();
        System.out.println( vector.add(3));
        vector.add(4);

        // now append the vector items to array list as
        arrayList.addAll(vector);
        printArrayListData(arrayList,"After Appending : ");

        //Copy elements of arraylist to Object array
        Object objArray[] = arrayList.toArray();
        printArrayData(objArray);

        //getSublist of Arraylist
        List list = arrayList.subList(1,3);
        printSubListData(list,"Sublist : ");

        //to replace any one item in arraylist use
        arrayList.set(2,0);

        //to check wheter the element is presenet in arraylist
        System.out.println("contains : "+arrayList.contains(2));

        //to check  the index of element in arraylist
        System.out.println("Index of  : "+arrayList.indexOf(2));

         printDataUsingIterator(arrayList,"Iterator : ");
    }

    private static void printSubListData(List list, String s) {
        System.out.println("\n"+s);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+" ");
        }
    }

    private static void printArrayData(Object[] objArray) {
        for (int i = 0; i < objArray.length; i++) {
            System.out.print(objArray[i]+" ");
        }
    }

    private static void printArrayListData(ArrayList arrayList,String string) {
        System.out.println("\n"+string);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i)+" ");
        }
    }

    private static void printDataUsingIterator(ArrayList arrayList, String s) {
        System.out.println("\n"+s);
        //Iterator itr = arrayList.iterator();
        ListIterator itr = arrayList.listIterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
