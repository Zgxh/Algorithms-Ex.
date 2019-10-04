/*
 * @lc app=leetcode.cn id=92 lang=java
 *
 * [92] 反转链表 II
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode node = new ListNode(0);
        node.next = head; // ****在head前添加一个节点，让第一次移动后指向head，同时保证新头节点不丢失
        ListNode current = node;
        // 首先找到倒序开始处理的节点，该节点的next指向需要处理的部分
        for (int i = 1; i < m; i++) {
            current = current.next;
        }
        ListNode start = current; // start.next始终指向倒序链表段的头结点
        ListNode end = null; // end指向当前需要交换的【链表对】的下个结点，防止丢失指针
        current = current.next;
        // 依次把当前需要处理的结点放到最前，并处理相关指针指向
        for (int i = m; i < n; i++) {
            end = current.next.next;
            current.next.next = start.next;
            start.next = current.next;
            current.next = end;
            // 交换完后，current指向不变，但自动相对原链表长度向右移动了一个位置
        }
        return node.next;
    }
}

