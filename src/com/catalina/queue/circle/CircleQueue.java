package com.catalina.queue.circle;

public class CircleQueue<E> {
    public int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
        elements[(front + size) % elements.length] = element;
        size++;

    }

    public E deQueue(E element) {
        E frontElement = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        return frontElement;
    }

    public E front() {
        return elements[front];
    }
}
