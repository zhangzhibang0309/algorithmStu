package com.catalina.arrayList;

import sun.font.FontRunIterator;

public class ArrayList<E> {
    /**
     * 元素的数量
     */
    private int size;
    /**
     * 所有的元素
     */
    private E[] elements;
    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 构造函数
     */
    public ArrayList(int capaticy) {
        // 判断 不能让容量太少
        capaticy = (capaticy < 10 ? DEFAULT_CAPACITY : capaticy);
        elements = (E[]) new Object[capaticy];
    }

    public ArrayList() { // 无参
        // 调用其他构造函数
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null; // 回收每个元素指向的对象的内存
        }
        size = 0;
        /*
        这里是清空 但是我们只是将size设置为0 看起来似乎是一种虚假的做法
        可能有人觉得这样clear之后还是占用内存 浪费内存 但这才恰恰是节约性能的一种做法
        如果你把里面的内存给销毁了 后面玩意要是在用到这个数组 又要list.add()，这样岂不是又要去重新申请内存 销毁内存 申请内存 你的性能就这样流失了
        而如果你把size设置成0 这样就足够了 此时你所有的操作都会因为size=0而无法进行 elements里面所有的元素你都访问不到
        当你需要再次使用这个数据结构的时候 add()会将第一个元素覆盖，size改成1，就可以访问到第一个元素 这样减少了很多不必要的性能浪费

        有人会觉得如果之前这个数组开辟了10w个 后面clear之后你不需要这么多 那你可以设置一个判断 size大于多少的时候销毁内存 小于这个数的时候size=0
         */
    }

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到尾部
     *
     * @param element
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return 原来的元素
     */
    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        // 确保容量够现在的size + 1，每次都确认一下就可，因为这个ensure函数里面检测如果size+1不大于容量的话就不会去扩容
        // 而且每次add都是一个元素 所以只要保证size+1不大于容量就可以了
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        size++;
        elements[index] = element;
    }

    /**
     * 删除index位置的元素
     *
     * @param index
     * @return
     */
    public int remove(int index) {
        rangeCheck(index);
        E old = elements[index];

        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;

        return 0;
    }

    public void remove(E element) {
        remove(indexOf(element));
    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    public int indexOf(E element) {
        // 单纯的遍历
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                // 这里用equals是因为泛型之后都是对象了 equals可以自定义相等规则
                // 不重写的话会去比较对象的地址 也就是看两个对象是不是完全一样
                // 而像integer这种 不重写他也是比较二者的值 也就是说 java自己把integer的equals重写了
                if (element.equals(elements[i])) return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    /**
     * 扩容
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量未旧的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements; // 改变了elements的指向

        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    private void outOfbounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ",Size:" + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfbounds(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfbounds(index);
        }
    }

    @Override
    public String toString() {
        // size=3, [11,22,33]
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");

        for (int i = 0; i < size; i++) {
            if (i != 0) string.append(",");

            string.append(elements[i]);
        }

        string.append("]");

        return string.toString();
    }
}
