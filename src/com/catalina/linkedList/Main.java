package com.catalina.linkedList;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayListCutDown<>();

        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        for (int i = 0; i < 50; i++) {
            list.remove(0);
        }
    }
}
