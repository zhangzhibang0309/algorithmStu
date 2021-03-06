package com.catalina.queue.circle;

import java.util.Arrays;

public class CircleQueue<E> {
    public int front;
    private int size;
    private E[] elements; // 用数组实现
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
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;

    }

    public E deQueue() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;

        return frontElement;
    }

    public E front() {
        return elements[front];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capacity=").append(elements.length)
        .append(" size=").append(size).append(",[");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");

        return string.toString();
    }

    /**
     * 扩容
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量未旧的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements; // 改变了elements的指向

        front = 0;
    }

    /**
     * 找到真实索引
     */
    public int index(int index) {
        // 优化了之前的取模运算
        // 因为index最大也不会>2 * elements.length
        // 举个例子 elements.length = 7，front指向6，6+7 < 2 * elements.length,这已经是index最大的情况了，所以可以这样去优化
        // index要>=0(0肯定可以，但是不能是负数，单端循环也不会出现负数) 然后elements.length也不能是0
        return index - (index >= elements.length ? elements.length : 0);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }
}
