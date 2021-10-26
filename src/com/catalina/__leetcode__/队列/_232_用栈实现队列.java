package com.catalina.__leetcode__.队列;


import java.util.Stack;

public class _232_用栈实现队列 {
    /**
     * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）
     * https://leetcode-cn.com/problems/implement-queue-using-stacks/
     */

    Stack<Integer> inStack;
    Stack<Integer> outStack;

    public _232_用栈实现队列() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        cheak();

        return outStack.pop();
    }

    public int peek() {
        cheak();

        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public void cheak() {
        while (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

}
