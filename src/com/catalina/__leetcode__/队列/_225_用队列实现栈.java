package com.catalina.__leetcode__.队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
public class _225_用队列实现栈 {
    class MyStack {
        Queue<Integer> queue;
        Queue<Integer> queuePop;

        public MyStack() {
            queue = new LinkedList<>();
            queuePop = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
        }

        public int pop() {
            int sizeOld = queue.size();
            for (int i = 0; i < sizeOld - 1; i++) {
                queuePop.offer(queue.poll());
            }
            while (!queuePop.isEmpty()) {
                queue.offer(queuePop.poll());
            }
            return queue.poll();
        }

        public int top() {
            int sizeOld = queue.size();
            for (int i = 0; i < sizeOld - 1; i++) {
                queuePop.offer(queue.poll());
            }
            int temp = queue.peek();
            queuePop.offer(queue.poll());
            while (!queuePop.isEmpty()) {
                queue.offer(queuePop.poll());
            }
            return temp;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

}
