package com.catalina.queue;


import com.catalina.queue.list.LinkedList;
import com.catalina.queue.list.List;

public class Deque<E> {

    private List<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enQueueRear(E element) {
        list.add(element);
    }

    public E deQueueFront() {
        return list.remove(0);
    }

    public void enQueueFront(E element) {
        list.add(0, element);
    }

    public E deQueueRear() {
        return list.remove(size() - 1);
    }

    public E front() {
        return list.get(0);
    }

    public E rear() {
        return list.get(size() - 1);
    }
}
