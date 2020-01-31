/**
 * 剑指offer第14题：链表中倒数第k个结点
 *
 * 输入一个链表，输出该链表中倒数第k个结点。
 *
 * @author Yu Yang
 * @create 2020-01-31 16:32
 */
public class Question_14 {

    /**
     * 思路：采用双指针。一个指针先走k个结点，然后两个指针一块走，直到快指针走到链表尾。
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k == 0) { // 别忘了这个判断
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k - 1; i++) { // 倒数第k个对应k - 1
            if (fast.next == null) {
                return null;
            }
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 链表结点定义
     */
    public class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }
}
