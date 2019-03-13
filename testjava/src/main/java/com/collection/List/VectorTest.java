package com.collection.List;

import java.util.Enumeration;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Created by amit on 29-08-2017.
 */
public class VectorTest
{
    public static void main(String args[])
    {
        Vector<String> list = new Vector();

        list.addElement("1");
        list.addElement("2");
        list.addElement("3");

        //System.out.println(list.firstElement());
        //System.out.println(list.lastElement());

        ListIterator<String> elements = list.listIterator(2);

        while (elements.hasNext())
        {
            System.out.println(elements.next());
        }

    }
}
