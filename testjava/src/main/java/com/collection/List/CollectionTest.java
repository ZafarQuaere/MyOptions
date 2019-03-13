package com.collection.List;

import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by amit on 14-08-2017.
 */
public class CollectionTest
{
    public static void main(String args[])
    {
        //testHashSet();
        //testHashMap();
        testIdentityHashMap();
        //commonTest();
    }

    private static void commonTest() {
        Map identityMap = new IdentityHashMap();
        Map hashMap = new HashMap();

        identityMap.put("a", 1);
        identityMap.put(new String("a"), 2);
        identityMap.put("a", 3);

        hashMap.put("a", 1);
        hashMap.put(new String("a"), 2);
        hashMap.put("a", 3);

        System.out.println("Identity Map KeySet Size :: " + identityMap);
        System.out.println("Hash Map KeySet Size :: " + hashMap);

        String temp = new String("a");
        String a = "a";

        System.out.println(temp.hashCode());

    }


    private static void testHashSet() {
        HashSet<Test> set = new HashSet<Test>();

        Test t1 = new Test(1);
        Test t2 = new Test(1);

        System.out.println(t1.hashCode() + "/" + t2.hashCode());
        System.out.println(t1.equals(t2));

        set.add(t1);
        set.add(t2);


        System.out.println(set);
    }

    private static void testHashMap()
    {
        HashMap<Test, String> map = new HashMap<Test, String>();

        Test t1 = new Test(1);
        Test t2 = new Test(1);

        System.out.println(t1.hashCode() + "/" + t2.hashCode());
        System.out.println(t1.equals(t2));

        map.put(t1, "t1");
        map.put(t2, "t2");

        System.out.println(map);
    }

    private static void testIdentityHashMap()
    {
        IdentityHashMap<Test, String> map = new IdentityHashMap<Test, String>();

        Test t1 = new Test(1);
        Test t2 = new Test(1);

        System.out.println(t1.hashCode() + "/" + t2.hashCode());
        System.out.println(t1.equals(t2));

        map.put(t1, "t1");
        map.put(t1, "t2");

        System.out.println(map.toString());
    }
}

class Test
{
    private int id;

    Test(int id)
    {
        this.id = id;
    }

    @Override
    public int hashCode() {

        int hashCode = id * 31;
        return hashCode;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Test)
        {
            if(id == ((Test)obj).id)
            {
                return true;
            }
        }
        return false;
    }
}
