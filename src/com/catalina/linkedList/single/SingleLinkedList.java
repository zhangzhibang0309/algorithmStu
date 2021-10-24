package com.catalina.linkedList.single;

import com.catalina.linkedList.AbstractList;

public class SingleLinkedList<E> extends AbstractList<E> {

    private Node<E> first; // 这算是链表的head 里面只存储了size 和first指针
    //first本身就是一个node，它指向了第一个node

    // 匿名内部类 链表中的每个节点
    private static class Node<E> {
        // 后面的每个node包含元素和next指针 还有一个node构造方法
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 清空
     */
    @Override
    public void clear() {
        size = 0; // 标记一下
        first = null; // first指向null 后面的指向全断了 所以全部node内存都被会手
    }

    /**
     * 获取某个索引的元素
     * 最好 O(1)
     * 最坏 O(n)
     * 平均 O(n)
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) { // O()
        return node(index).element;
    }

    /**
     * 设置某个索引的元素
     * 最好 O(1)
     * 最坏 O(n)
     * 平均 O(n)
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) { // O()
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;

        return old;
    }

    /**
     * 添加节点
     * 最好 O(1)
     * 最坏 O(n)
     * 平均 O(n)
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == 0) {
            first = new Node<>(element, first);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<E>(element, prev.next);
        }


        size++;
    }

    /**
     * 删除某个节点
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = first;
        if (index == 0) {
            first = first.next;
        }else {
            Node<E> prev = node(index - 1);
            prev.next = prev.next.next;
        }

        size--;
        return node.element;
    }


    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;

                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;

                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取index位置对应节点对象
     * 最好 O(1)
     * 最坏 O(n)
     * 平均 O(n)
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");

        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) string.append(",");

            string.append(node.element);
            node = node.next;
        }
        string.append("]");

        return string.toString();
    }
}

