/*
 * @lc app=leetcode.cn id=141 lang=java
 *
 * [141] 环形链表
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        /**
         * 设置快慢指针。快指针的移动速度是慢指针的二倍。
         * 如果快指针遇到null了，则没有环；如果快指针追上慢指针了，说明有环。
         */
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            if (fast.next == null) {
                return false;
            }
            fast = fast.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}

