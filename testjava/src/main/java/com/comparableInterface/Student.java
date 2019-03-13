package com.comparableInterface;

/**
 * Created by parasmani.sharma on 21/10/2016.
 */
public class Student implements Comparable<Student> {

    int rollNo;
    String studentName;

    public Student(String name, int i) {
         this.studentName = name;
         this.rollNo = i;
    }

    @Override
    public String toString() {
        return studentName + "/" + rollNo;
    }

    @Override
    public boolean equals(Object o) {

        if(o == null)
        {
            return false;
        }
        Student student = (Student) o;

        return this.rollNo == student.rollNo;
    }

    @Override
    public int hashCode() {
        return this.rollNo;
    }

    @Override
    public int compareTo(Student obj) {

        if(this.rollNo < obj.rollNo)
        {
            return -1;
        }
        else if(this.rollNo > obj.rollNo)
        {
            return 1;
        }
        else
        {
            return 0;
        }

    }
}
