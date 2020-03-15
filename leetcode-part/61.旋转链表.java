/*
 * @lc app=leetcode.cn id=61 lang=java
 *
 * [61] 旋转链表
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
    public ListNode rotateRight(ListNode head, int k) {
        ListNode newnode = head;
        if (head == null) return null;
        int length = 1;
        while (newnode.next != null) {
            length += 1;
            newnode = newnode.next;
        }
        newnode.next = head;
        for (int i=0; i<length-k%length; i++) {
            newnode = head;
            head = head.next;
        }
        newnode.next = null;
        return head;
    }
}

