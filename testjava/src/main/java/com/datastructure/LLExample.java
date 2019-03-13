package com.datastructure;

/**
 * Created by amit on 17-10-2016.
 */
public class LLExample
{
    public static void main(String args[])
    {
        LL ll = new LL();
        ll.insert(1, 1);
        ll.insert(2, 2);
        ll.insert(3, 3);
        ll.insert(4, 4);
        ll.insert(5, 5);
        ll.insert(6, 6);

        System.out.println("size : " + ll.size());

        ll.printLinkedList();

        while (!ll.isEmpty())
        {
            ML delete = ll.delete();
            System.out.print("deleted link : ");
            delete.printLink();
            System.out.println();
        }

    }
}

class ML
{
    private int data1;
    private int data2;
    public ML pl;

    public ML(int data1, int data2)
    {
        this.data1 = data1;
        this.data2 = data2;
    }

    public void printLink()
    {
        System.out.print("{" + this.data1 + ", " + this.data2 + "}");
    }
}


class LL
{
    private ML cl;
    private ML ll;

    public LL()
    {
        cl = ll = null;
    }

    public void insert(int d1, int d2)
    {
        ML ml = new ML(d1, d2);
        ml.pl = cl;
        cl = ml;
        ll = ml;
    }

    public void printLinkedList()
    {
        System.out.print("List : ");
        while(cl != null)
        {
            cl.printLink();
            cl = cl.pl;
        }
        reset();
    }

    public boolean isEmpty()
    {
        return  cl == null;
    }

    public ML delete()
    {
        ML temp = cl;
        cl = cl.pl;
        return temp;
    }

    public int size()
    {
        int count = 0;
        while(cl != null)
        {
            count++;
            cl = cl.pl;
        }
        reset();
        return count;
    }

    private void reset()
    {
        cl = ll;
    }

}












