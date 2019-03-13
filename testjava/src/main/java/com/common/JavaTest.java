package com.common;

/**
 * Created by amit on 12-08-2017.
 */
public class JavaTest
{
    public static void main(String args[])
    {
        Building b1 = new Building("b1", 1);
        Building b2 = new Building("b1", 1);

        Building b3 = b1;

        System.out.println("hashcode b1 : " + b1.hashCode());
        System.out.println("hashcode b2 : " + b2.hashCode());
        System.out.println("hashcode b3 : " + b3.hashCode());

        System.out.println("both equal : " + b1.equals(b2));

        //System.out.println("both equal : " + (b3 == b1));
    }
}
