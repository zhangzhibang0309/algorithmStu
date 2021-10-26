package com.catalina.linkedList;

import com.catalina.linkedList.single.SingleCircleLinkedList;

import java.awt.*;

public class Main {

    static void josephus() {
        BiDirectionalnalCircleLinkedList<Integer> list = new BiDirectionalnalCircleLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }
        list.reset();
        while (!list.isEmpty()) {
            list.next();
            list.next();
            System.out.println(list.remove());
        }
    }

    public static void main(String[] args) {
//        List<Integer> list = new BiDirectionalnalCircleLinkedList<>();
//
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        System.out.println(list);
        josephus();
    }
}
