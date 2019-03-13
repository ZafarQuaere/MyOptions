package com.string;

/**
 * Created by zafar.imam on 18-08-2017.
 */

public class ObjectTest {

    String a;

    public ObjectTest(String ab) {
        a = ab;
    }


    public boolean equals(Object object2) {
        if(a == object2) {
            return true;
        }
        else return false;
    }



    public boolean equals2(Object object2) {
        if(a.equals(object2)) {
            return true;
        }

        else return false;
    }




    public static void main(String[] args) {

        ObjectTest object1 = new ObjectTest("test");
        ObjectTest object2 = new ObjectTest("test");

       object1.equals(object2);
        System.out.println(object1.getClass().equals(object2.getClass()));

      /*  object1.equals2(object2);
        System.out.println(object1.equals2(object2));*/
    }


}
