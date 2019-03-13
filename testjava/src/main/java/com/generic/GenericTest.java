package com.generic;

/**
 * Created by amit on 03-01-2017.
 */
public class GenericTest
{
    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        Box<String> stringBox = new Box<String>();

        integerBox.add(new Integer(10));
        stringBox.add(new String("Hello World"));

        System.out.printf("Integer Value :%d\n\n", integerBox.get());
        System.out.printf("String Value :%s\n", stringBox.get());
    }

    private static class Box<T>
    {
        private T t;

        public void add(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }
    }
}
