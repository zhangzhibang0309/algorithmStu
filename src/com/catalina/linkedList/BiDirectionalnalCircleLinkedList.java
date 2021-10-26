package com.catalina.linkedList;

import com.sun.deploy.ui.UIFactory;

public class BiDirectionalnalCircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first; // 这算是链表的head 里面只存储了size 和first指针
    //first本身就是一个node，它指向了第一个node
    private Node<E> last; // 指向尾节点
    private Node<E> current;// 为了解决约瑟夫问题

    /**
     * Node内部类
     * 与单向链表不同点 多了个prev指针
     *
     * @param <E>
     */
    // 匿名内部类 链表中的每个节点
    private static class Node<E> {
        // 后面的每个node包含元素和next指针 还有一个node构造方法
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }

            sb.append("_").append(element).append("_");

            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }

    /**
     * 后面是为了解决约瑟夫问题补充的方法
     */
    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) return null;

        current = current.next;
        return current.element;
    }

    public E remove() {
        if (current == null) return null;

        Node<E> next = current.next;
        E elements = remove(current);
        if (size == 0) { // 当删除完发现已经没有元素的时候
            current = null;// 因为上面next还是指向自己 所以这个节点不会被删除 所以直接让他指向null就可
        }else {
            current = next;// 链表没空 next指向下一个节点
        }
        return elements;
    }

    /**
     * 清空
     * 与单向不同点 last也要指向null 没有gc root指针指向 node内存回收
     */
    @Override
    public void clear() {
        size = 0; // 标记一下
        first = null; // first指向null 后面的指向全断了 所以全部node内存都被回收
        last = null;
    }

    /**
     * 获取某个索引的元素
     * 与单向没有不同点
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
     * 与单向没有不同点
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
     * 有很大的不同点。。
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) { // 往最后面添加元素
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, first);
            if (oldLast == null) { // 这是链表为空的时候 index == size == 0
                first = last;
                first.next = first;
                first.prev = first;
            } else { // 这是在链表不为空的情况下在最后面添加元素的情况
                oldLast.next = last;
                first.prev = last;
            }
        } else { // 往最前面或者中间添加元素
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;
            prev.next = node;


            if (next == first) { // 这是往最前面添加节点的情况
                first = node;
            }

        }

        size++;
    }


    /**
     * 删除 传参为node节点
     * @param node
     * @return
     */
    public E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (node == first) { // 说明index == 0 要删除第一个节点
                first = next;
            }

            if (node == last) { // index == size - 1 要删除最后一个节点
                last = prev;
            }
        }

        size--;
        return node.element;
    }
    /**
     * 删除某个节点
     * 不同点比较大
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
    }

    /**
     * 查找元素索引
     * 与单向链表相比 也不需要改变什么
     *
     * @param element
     * @return
     */
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
     * 不同点 也是因为多了prev可以往前找
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        if (index < (size >> 2)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    /**
     * 这里也不需要做改变
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");

        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) string.append(",");

            string.append(node); // 这里改成了node 所以要去重写一下node内部类的tostring
            node = node.next;
        }
        string.append("]");

        return string.toString();
    }
}

