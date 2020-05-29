package com.datastructure;

/**
 * Created by parasmani.sharma on 09/06/2017.
 */

public class QueueExample {

    public static void main(String args[]) {
        Queue queue = new Queue(1000);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);

        System.out.println(queue.dequeue() +" dequeued from queue\n");

        System.out.println("Front item is " + queue.front());

        System.out.println("Rear item is " + queue.rear());
    }
}

// Java program for array implementation of queue

// A class to represent a queue
class Queue {
    int front, rear, size;
    int capacity;
    int array[];

    public Queue(int capacity) {
        capacity = capacity;
        front = size = 0;
        rear = capacity - 1;
        array = new int[capacity];

    }

    // Queue is full when size becomes equal to
    // the capacity
    boolean isFull() {
        return (size == capacity);
    }

    // Queue is empty when size is 0
    boolean isEmpty() {
        return (size == 0);
    }

    // Method to add an item to the queue.
    // It changes rear and size
    void enqueue(int item) {
        if (isFull())
            return;
        rear = (rear + 1) % capacity;
        System.out.print("\n" + rear);

        array[rear] = item;
        size = size + 1;
        System.out.println("\n" + item + " enqueued to queue");
    }

    // Method to remove an item from queue.
    // It changes front and size
    int dequeue() {
        if (isEmpty())
            return Integer.MIN_VALUE;

        int item = array[front];
        front = (front + 1) % capacity;
        size = size - 1;
        return item;
    }

    // Method to get front of queue
    int front() {
        if (isEmpty())
            return Integer.MIN_VALUE;

        return array[front];
    }

    // Method to get rear of queue
    int rear() {
        if (isEmpty())
            return Integer.MIN_VALUE;

        return array[rear];
    }
}