package com.catalina.__leetcode__.链表;

public class _206_反转链表 {

    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead.next;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    public ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
