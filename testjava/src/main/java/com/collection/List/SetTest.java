package com.collection.List;

import java.util.HashSet;

/**
 * Created by amit on 29-08-2017.
 */
public class SetTest
{
    public static void main(String args[])
    {
        //HashSet LinkedHashSet SortedSet NavigableSet TreeSet
        testHashSet();
    }

    private static void testHashSet()
    {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        hashSet.add("2");
        hashSet.add("3");
        hashSet.add("4");
        hashSet.add("4");

        System.out.println(hashSet);
    }
}
