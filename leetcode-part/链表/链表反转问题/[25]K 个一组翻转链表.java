//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表 
// 👍 889 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * 设置前后指针，分离每次需要处理的k长的链表，处理完后再合到原链表中
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 前后指针
        ListNode former = dummy;
        ListNode latter = dummy;
        while (former.next != null) {
            // 确定一组k个被反转的链表结点
            for (int i = 0; i < k; i++) {
                if (former.next != null) {
                    former = former.next;
                } else {
                    // 一组不够k个了，就不反转
                    return dummy.next;
                }
            }
            // 保存下一段的开头
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

    /*
     * 把长度为k的链表反转
     */
    private ListNode[] reverseKList(ListNode head) {
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
//leetcode submit region end(Prohibit modification and deletion)
