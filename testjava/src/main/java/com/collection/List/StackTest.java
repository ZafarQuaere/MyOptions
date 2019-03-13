package com.collection.List;

import java.util.Stack;

/**
 * Created by amit on 29-08-2017.
 */
public class StackTest
{
    public static void main(String args[])
    {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        System.out.println("pop item : " + stack.pop());

        System.out.println("peek item : " + stack.peek());
        System.out.println("peek item : " + stack.peek());

        System.out.println(stack);

    }
}
