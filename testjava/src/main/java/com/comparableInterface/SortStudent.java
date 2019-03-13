package com.comparableInterface;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;

/**
 * Created by parasmani.sharma on 21/10/2016.
 */
public class SortStudent {

    public static void main(String[] args)
    {
        testTreeSet();




        /*ArrayList<Student> data = new ArrayList();
        data.add(new Student("Rao", 49));
        data.add(new Student("Zafar", 101));
        data.add(new Student("Paras", 102));

        Collections.sort(data);
        for(Student st:data){
            System.out.println(st.studentName+" "+st.rollNo);
        }*/

    }

    private static void testTreeSet() {

        ArrayList<Student> data = new ArrayList();
        data.add(new Student("a", 1));
        data.add(new Student("b", 3));
        data.add(new Student("c", 2));
        data.add(new Student("c", 50));
        data.add(new Student("c", 3));
        data.add(new Student("c", 6));
        data.add(new Student("c", 10));

        System.out.println(data);

        TreeSet<Student> treeSet = new TreeSet<>(data);

        System.out.println(treeSet);

    }

}
