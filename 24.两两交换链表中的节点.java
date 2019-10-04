/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
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
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode ptr = pre;
        while (ptr.next != null && ptr.next.next != null) {
            ListNode temp = ptr.next;
            ptr.next = temp.next;
            ptr = ptr.next;
            temp.next = ptr.next;
            ptr.next = temp;
            ptr = temp;
        }
        return pre.next;
    }
}

