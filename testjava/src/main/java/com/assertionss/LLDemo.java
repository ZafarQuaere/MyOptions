package com.assertionss;

/**
 * Created by zafar.imam on 01-07-2017.
 */

public class LLDemo {

    static Node node;
    private static LLDemo llDemo;

    public static void main(String arg[]){

        llDemo = new LLDemo();

        llDemo.node = new Node(10);
        Node node1 = new Node(20);
        Node node2 = new Node(30);
        Node node3 = new Node(40);
        Node node4 = new Node(50);

        llDemo.node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        while (node != null){
            System.out.println("Data's : "+node.data);
            node = node.next;


        }

        insertData(node);


    }

    private static void insertData(Node node) {

    }

}
