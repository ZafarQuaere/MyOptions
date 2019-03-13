package com.collection.List;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zafar.imam on 19-08-2017.
 */

public class HashTest
{
    public static void main(String ar[])
    {
        HashMap<Student, String> map = new HashMap<>();

        Student s1 = new Student(1);
        Student s2 = new Student(2);
        Student s3 = new Student(3);
        Student s4 = new Student(4);
        Student s10 = new Student(10);
        Student s20 = new Student(20);

        System.out.println(s1.hashCode() + "/" + s2.hashCode()); //1/2
        System.out.println(s1.equals(s2));   //false

        System.out.println(map.put(s1, "s1"));
        System.out.println(map.put(s2, "s2"));
        System.out.println(map.put(s3, "s3"));
        System.out.println(map.put(s4, "s4"));
        System.out.println(map.put(s10, "s10"));
        System.out.println(map.put(s20, "s20"));

        System.out.println(map);

        System.out.println("Single Value : "+map.get(s10));
        retrieveData(map);

    }

    private static void retrieveData(HashMap<Student, String> map) {

        //retriving data using iterator
       /* Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("Key :"+entry.getKey()+"  Value: "+entry.getValue());
        }*/

        //retrieving data using for
        for (Map.Entry entr : map.entrySet()) {
            System.out.println("Key :"+entr.getKey()+"  Value: "+entr.getValue());
        }
    }
}

class Student
{
    private int rollNo;

    Student(int rollNo)
    {
        this.rollNo = rollNo;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;
        if(this.rollNo == ((Student)o).rollNo)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return rollNo;
    }
}
