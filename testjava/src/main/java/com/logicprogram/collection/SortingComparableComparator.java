package com.logicprogram.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by parasmani.sharma on 18/09/2017.
 */

public class SortingComparableComparator {

    public static void main(String args[]) {
        /*
         * By refering Optiontown Seat fragment. We can suggest that :
         *
         * 1) For Comparable : The array type from collection framework if doing default sorting order
         * (i.e TreeSet Collection in our case inside Seat Fragment) then compareTo() overriden method
         * will work for them inside model class. If, otherwise, the array type (i.e ArrayList) from collection
         * framework is not doing any sorting by default then it will not make use of compareTo() overriden method inside model class.
         *
         * */


        ArrayList<Students> student = new ArrayList<>();
        student.add(new Students("Ram", 5));
        student.add(new Students("Shyam", 4));
        student.add(new Students("Alex", 2));
        student.add(new Students("Paras", 1));
        student.add(new Students("Zafar", 3));
        student.add(new Students("Amit", 6));


        // by deafult TreesSet implement sortin so it will make use of compareTo() method in model class.
        TreeSet<Students> treeSet = new TreeSet<>(student);


        // by deafult ArrayList did not do sorting so it will not make use of compareTo() method in model class. So we need to use
        // collection framework sorting approach.
        //Collections.sort(student);

        Iterator<Students> itr = treeSet.iterator();
        while (itr.hasNext()) {
            Students std = (Students) itr.next();
            System.out.println(std.getName() + " " + std.getRollNumb());
        }


        // by using comparator interface . Remove compareTo from model class.
        //Collections.sort(student, new SortStudent_Comparator());   // using

        /*for (Students std :student) {
            System.out.println(std.getName()+ "    " + std.getRollNumb());
        }*/


    }

    static class Students implements Serializable, Comparable<Students> {
        String name;
        int rollNumb;

        public Students(String name, int rollnumber) {
            this.name = name;
            this.rollNumb = rollnumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRollNumb() {
            return rollNumb;
        }

        public void setRollNumb(int rollNumb) {
            this.rollNumb = rollNumb;
        }

        @Override
        public int compareTo(Students o) {
            /*if(this.getRollNumb()<o.getRollNumb())
            {
             return -1;
            }else if(this.getRollNumb()<o.getRollNumb()){
                return 1;
            }
            return 0;*/
            return this.getRollNumb() - o.getRollNumb();
        }
    }

    static class SortStudentComparator implements Comparator<Students> {

        @Override
        public int compare(Students students, Students t1) {
            return -(students.getName().compareTo(t1.getName()));
        }
    }


}
