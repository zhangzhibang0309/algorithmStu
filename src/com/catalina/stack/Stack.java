package com.catalina.stack;

import com.catalina.stack.list.ArrayList;
import com.catalina.stack.list.List;

public class Stack<E> {

    /**
     * 为什么这里是在类里面直接new一个arraylist呢而不继承呢 因为继承过来的话 有些很不兼容的api
     * 这个是基于arraylist实现的 当然也可以用链表
     */

    private List<E> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(size()-1);
    }

    public E top() {
        return list.get(size() - 1);
    }

}
