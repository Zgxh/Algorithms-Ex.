/*
 * @lc app=leetcode.cn id=19 lang=java
 *
 * [19] 删除链表的倒数第N个节点
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 双指针问题，前后指针同时往后移动
         */
        if (head == null) {
            return null;
        }
        // 开头新建一个结点，防止头指针丢失
        ListNode pointer = new ListNode(-1);
        pointer.next = head;
        ListNode former = pointer;
        ListNode latter = pointer;
        // 前后指针相差 n+1， 这样能保证后指针的指向的节点可以修改指向
        for (int i = 0; i < n; i++) {
            former = former.next;
        }
        while (former.next != null) {
            former = former.next;
            latter = latter.next;
        }
        latter.next = latter.next.next;
        return pointer.next;
    }
}

