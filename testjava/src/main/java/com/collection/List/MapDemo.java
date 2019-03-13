package com.collection.List;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zafar.imam on 11-08-2017.
 */

public class MapDemo {

    public static void main(String arg[]){
        HashMap<String,Integer> map = new HashMap<>();
        //addData(map);

        System.out.println();

        addNonGenericData();
       // retrieveData(map);

        System.out.println();

       // retrieveData1(map);

    }

    private static void addNonGenericData() {
        Map map = new HashMap();
        System.out.println(map.put(1,"Name")); // will return null as key is unique
        System.out.println(map.put(2,"Add")); // will return null as key is unique
        System.out.println(map.put(3,"Company")); // will return null as key is unique
        System.out.println(map.put(2,"Location")); //this will return Add as the key is duplicate
        System.out.println(map.put(5,"salary")); // will return null as key is unique
        System.out.println();
        retriveNonGenericThrouhIterator(map);

    }

    //retrieving data from Map using iterator
    private static void retriveNonGenericThrouhIterator(Map map) {
        Set set = map.entrySet();
        Iterator itr = set.iterator();

        while (itr.hasNext()){
            Map.Entry entry = (Map.Entry) itr.next();
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

    }

    private static void addData(Map<String, Integer> map) {

        System.out.println(map.put("name",5));
        System.out.println(map.put("company",15));
        System.out.println(map.put("add",25));
        System.out.println(map.put("mobile",35));
    }

    private static void retrieveData1(Map<String, String> map) {

        for (Map.Entry<String,String> m : map.entrySet()) {
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }

    private static void retrieveData(Map<String, String> map) {

        Set<Map.Entry<String, String>> entries = map.entrySet();

        for (Map.Entry<String, String> e: entries){

            System.out.println("Key :"+e.getKey()+"  Value :"+e.getValue());

        }


    }
}
