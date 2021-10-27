package com.catalina.queue;

import com.catalina.queue.circle.CircleDueue;
import com.catalina.queue.circle.CircleQueue;


public class Main {

    public static void main(String[] args) {
        CircleDueue<Integer> queue = new CircleDueue<>();

        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i+1);
            queue.enQueueRear(i+100);
        }

        for (int i = 0; i < 3; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }
        queue.enQueueFront(11);
        queue.enQueueFront(12);
        System.out.println(queue);
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueFront());
        }
    }
}
