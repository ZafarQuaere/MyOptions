package com.sun;

public class NotThreadSafe{
    StringBuilder builder = new StringBuilder();

    public void add(String text){
        this.builder.append(text);
        print();
    }

    private void print() {
        System.out.println(builder.toString());
    }


}
