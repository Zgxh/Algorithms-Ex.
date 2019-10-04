/*
 * @lc app=leetcode.cn id=21 lang=java
 *
 * [21] 合并两个有序链表
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null? l2 : l1;
        }
        ListNode l3 = new ListNode(0);
        ListNode ptr = l3;
        while (l1 != null) {
            while (l2 != null && l1.val > l2.val) {
                ptr.next = new ListNode(l2.val);
                l2 = l2.next;
                ptr = ptr.next;
            }
            ptr.next = new ListNode(l1.val);
            l1 = l1.next;
            ptr = ptr.next;
        }
        ptr.next = l2;
        l3 = l3.next;
        return l3;
    }
}

