package com.catalina.queue.circle;

import javax.swing.plaf.metal.MetalIconFactory;

public class CircleDueue<E> {
    public int front;
    private int size;
    private E[] elements; // 用数组实现
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;

    }

    public E deQueueFront() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;

        return frontElement;
    }

    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size++;

    }

    public E deQueueRear() {
        int rearIndex = index(size - 1);
        E rear = elements[rearIndex];
        elements[rearIndex] = null;
        size--;

        return rear;
    }

    public E front() {
        return elements[front];
    }

    public E rear() {
        return elements[index(front + size)];
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
        index += front;
        if (index < 0) {
            return index + elements.length;
        }

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
