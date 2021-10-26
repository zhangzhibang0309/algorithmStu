package com.catalina.queue;

import java.util.zip.DeflaterOutputStream;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);
        queue.enQueue(55);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }
}
