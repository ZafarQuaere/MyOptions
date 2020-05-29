package com.others;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class RemoveDuplicatesLL {

    Node head;

    public static void main(String[] arg) {
//        removeDuplicateFromLL();
        RemoveDuplicatesLL llist = new RemoveDuplicatesLL();
        llist.push(20);
        llist.push(13);
        llist.push(13);
        llist.push(11);
        llist.push(11);
        llist.push(11);


        System.out.println("List before removal of duplicates");
        llist.printList();

        llist.removeDuplicates();

        System.out.println("List after removal of elements");
        llist.printList();
    }

    private static void removeDuplicateFromLL() {
        LinkedList ll = new LinkedList();
        ll.push(20);
        ll.push(13);
        ll.push(13);
        ll.push(11);
        ll.push(11);
        ll.push(11);

        //now add this data to hashset
        Set set = new HashSet();
        for (int i = 0; i < ll.size(); i++) {
            set.add(ll.get(i));
        }

        Integer[] arr = new Integer[set.size()];
        set.toArray(arr);
        Arrays.sort(arr);
        System.out.println(">>> >> "+Arrays.toString(arr));
    }

    void removeDuplicates() {
        Node curr = head;
        while (curr != null) {
            Node temp = curr;
            while (temp != null && temp.data == curr.data) {
                temp = temp.next;
            }
            curr.next = temp;
            curr = curr.next;
        }
    }


    public void push(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
}
