package com.catalina.linkedList;

public abstract class AbstractList<E> implements List<E>{

    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    // 这个出现了均摊复杂度 大部分时间都是1 然后偶尔出现很高 n次的时候 把n均摊给前面的1 正好2n -> 2n/n -> O(1)
    // 最好 O(1)
    // 最坏 O(n)
    // 平均 O(1)
    // 均摊 O(1)
    public void add(E element) {
        add(size, element);
    }

    protected void outOfbounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfbounds(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfbounds(index);
        }
    }
}
