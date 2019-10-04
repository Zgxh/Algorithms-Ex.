import java.util.Comparator;
/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个排序链表
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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(-1);
        // 建立小顶堆并使用lambda表达式定义comparator：按照val升序存储
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(Comparator.comparingInt(node -> node.val));
        // 遍历lists,把非空的部分【头结点】压入堆中，为了减小堆长度，减少时间复杂度
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        ListNode pointer = result;
        // 每次拿出最小的元素，并将下一个结点加入堆中
        while (!queue.isEmpty()) {
            pointer.next = queue.poll();
            pointer = pointer.next;
            if (pointer.next != null) {
                queue.add(pointer.next);
            }
        }
        return result.next;
    }
}


