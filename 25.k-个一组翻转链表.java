/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] K 个一组翻转链表
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
    public ListNode reverseKGroup(ListNode head, int k) {
        /**
         * 设置前后指针，分离每次需要处理的k长的链表，处理完后再合到原链表中
         */
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 前后指针
        ListNode former = dummy;
        ListNode latter = dummy;
        while (former.next != null) {
            // 把前指针移动k个位置
            for (int i = 0; i < k; i++) {
                if (former.next != null) {
                    former = former.next;
                }
                else {
                    return dummy.next;
                }
            }
            // 新开辟指针指向该[k个节点段]的下个节点，防止指针丢失
            ListNode end = former.next;
            former.next = null;
            ListNode[] lastKArray = reverseKList(latter.next);
            // 把反转完的k长的链表连接到latter与end之间
            latter.next = lastKArray[0];
            lastKArray[1].next = end;
            // 重新初始化前后指针的指向，同时指向end的前一个节点
            former = lastKArray[1];
            latter = lastKArray[1];
        }
        return dummy.next;
    }
    private ListNode[] reverseKList(ListNode head) {
        /*
         * 把长度为k的链表反转
         */
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode current = head;
        while (current.next != null) {
            ListNode end = current.next.next;
            current.next.next = dummy.next;
            dummy.next = current.next;
            current.next = end;
        }
        return new ListNode[] {dummy.next, current};
    }
}

