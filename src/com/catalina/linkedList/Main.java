package com.catalina.linkedList;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new BiDirectionalnalLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.remove(3);
        System.out.println(list);
        System.out.println(list.indexOf(6));
        list.set(4,100);
        list.add(0,99);
        System.out.println(list);
    }
}
