package com.examples.linkedlist;

/**
 * Created by amit on 06-06-2017.
 */
public class LinkedListTest
{
    public static void main(String args[])
    {
        LinkedList linkedList = new LinkedList();
        linkedList.addFirst(new Node(0, null));
        linkedList.addLast(new Node(1, null));
        linkedList.addLast(new Node(2, null));
        Node node3 = new Node(3, null);
        linkedList.addLast(node3);
        linkedList.addLast(new Node(4, null));
        linkedList.addLast(new Node(5, null));
        linkedList.addLast(new Node(6, null));
        linkedList.insertAfter(node3, new Node(33, null));


        linkedList.travers();
        //linkedList.removeLast();
        //linkedList.removeFirst();
        //linkedList.removeAfter(node3);
        linkedList.remove(node3);
        //linkedList.travers();
        //linkedList.remove(node3);
        linkedList.travers();


    }
}


class LinkedList
{
    private Node head;
    private Node tail;

    public void remove(Node node)
    {
        if(head == null || node == null)
        {
            return;
        }
        else
        {
            if(head == node)
            {
                removeFirst();
            }
            else if(tail == node)
            {
                removeLast();
            }
            else
            {
                Node previous = head;
                while(previous != null && previous.next != node)
                {
                    previous = previous.next;
                }
                if(previous != null)//null means moved to last but did not find node to remove. given node is not in the list
                {
                    previous.next = node.next;
                }
            }
        }
    }

    public void removeAfter(Node after)
    {
        if(head == null || after == null || tail == after)
        {
            return;
        }
        else
        {
            Node target = after.next;
            after.next = target.next;
        }
    }

    public void removeFirst()
    {
        if(head == null)
        {
            return;
        }
        else
        {
            head = head.next;
        }
    }

    public void removeLast()
    {
        if(head == null)
        {
            return;
        }
        else
        {
            if(head == tail)
            {
                head = null;
                tail = null;
            }
            else
            {
                Node previousToTail = head;

                while(previousToTail.next != tail)
                {
                    //tail = previousToTail.next;
                    //tail.next = null;
                    previousToTail = previousToTail.next;
                }
                tail = previousToTail;
                tail.next = null;

            }
        }
    }


    public void travers()
    {
        int count = 0;
        Node current = head;
        while(current != null)
        {
            System.out.println("traversed element at " + count + " >> [" + current.data + ", " + current.next + "]");
            count++;

            current = current.next;
        }
    }


    public void insertAfter(Node previous, Node newNode)
    {
        if(newNode == null)
        {
            return;
        }
        else
        {
            if(previous == null)
            {
                addFirst(newNode);
            }
            else
            {
                if(previous == tail)
                {
                    addLast(newNode);
                }
                else
                {
                    Node next = previous.next;
                    previous.next = newNode;
                    newNode.next = next;
                }
            }
        }
    }

    public void addLast(Node newNode)
    {
        if(newNode == null)
        {
            return;
        }
        else
        {
            if(head == null)
            {
                newNode.next = null;
                head = newNode;
                tail = newNode;
            }
            else
            {
                newNode.next = null;
                tail.next = newNode;
                tail = newNode;
            }
        }
    }

    public void addFirst(Node newNode)
    {
        if(newNode == null)
        {
            return;
        }
        else
        {
            if(head == null)
            {
                newNode.next = null;
                head = newNode;
                tail = newNode;
            }
            else
            {
                newNode.next = head;
                head = newNode;
            }
        }
    }
}

class Node
{
    int data;
    Node next;

    public Node(int data, Node next)
    {
        this.data = data;
        this.next = next;
    }
}
