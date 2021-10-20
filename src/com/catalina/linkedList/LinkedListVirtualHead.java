package com.catalina.linkedList;

/**
 * 增加一个虚拟头节点
 *
 * @param <E>
 */
public class LinkedListVirtualHead<E> extends AbstractList<E> {

    private Node<E> first; // 这算是链表的head 里面只存储了size 和first指针
    //first本身就是一个node，它指向了第一个node

    // 构造函数
    // 主要是为了创造这个虚拟头节点
    public LinkedListVirtualHead() {
        first = new Node<>(null, null);
    }

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
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        return node(index).element;
    }

    /**
     * 设置某个索引的元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;

        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node<E> prev = index == 0 ? first : node(index - 1);
        prev.next = new Node<E>(element, prev.next);

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> prev = index == 0 ? first : node(index - 1);
        Node<E> oldNode = prev.next;// 被删除的节点 暂存一下
        prev.next = oldNode.next;
        size--;
        return oldNode.element;
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
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");

        Node<E> node = first.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) string.append(",");

            string.append(node.element);
            node = node.next;
        }
        string.append("]");

        return string.toString();
    }
}

