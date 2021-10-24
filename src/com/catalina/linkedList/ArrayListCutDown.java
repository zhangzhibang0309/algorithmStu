package com.catalina.linkedList;

/**
 * 对于复杂度分析 一般分为 最好 最坏 平均情况复杂度
 * 动态数组缩容
 *
 * @param <E>
 */
public class ArrayListCutDown<E> extends AbstractList<E>{
    /**
     * 所有的元素
     */
    private E[] elements;
    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 构造函数
     */
    public ArrayListCutDown(int capaticy) {
        // 判断 不能让容量太少
        capaticy = (capaticy < 10 ? DEFAULT_CAPACITY : capaticy);
        elements = (E[]) new Object[capaticy];
    }

    public ArrayListCutDown() { // 无参
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
     * 获取index位置的元素
     * 查 都是常数级别 O(1)
     *
     * @param index
     * @return
     */
    //
    public E get(int index) {
        rangeCheck(index);
        return elements[index]; // 由于数组在内存中的特点 随机访问速度特别快
    }

    /**
     * 设置index位置的元素
     * 改 O(1)
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
     * 增
     * 最好 O(1)
     * 最坏 O(n)
     * 平均 O((n+1)/2) -> O(n)
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
     * 删
     * 最好 O(1)
     * 最坏 O(n)
     * 平均 O((n+1)/2) -> O(n)
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];

        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;

        trim();

        return old;
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


    /**
     * 缩容
     *
     * @return
     */
    private void trim() {
        int oldCapacity = elements.length;

        // 这个地方缩容需要注意 缩容的值 *
        int newCapacity = oldCapacity / 4;

        if ((size >= newCapacity) || oldCapacity <= DEFAULT_CAPACITY) return;

        // 剩余空间还很多
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

        System.out.println(oldCapacity+"缩容为"+newCapacity);
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
