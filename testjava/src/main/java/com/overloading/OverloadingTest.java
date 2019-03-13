package com.overloading;

/**
 * Created by amit on 30-08-2017.
 */
public class OverloadingTest
{
    public static void main(String... args)
    {
        Parent parent = new Parent();

        Child1 c1 = (Child1) parent;
        Child2 c2 = (Child2) parent;





    }
}

class Parent
{

}

class Child1 extends Parent
{

}


class Child2 extends Parent
{

}