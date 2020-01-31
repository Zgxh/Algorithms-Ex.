
/**
 * 剑指offer第15题：反转链表
 *
 * 输入一个链表，反转链表后，输出新链表的表头。
 *
 * @author Yu Yang
 * @create 2020-01-31 16:45
 */
public class Question_15 {

    /**
     * 思路：新建一个链表，从原链表开始用头插法依次插入
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(head.val);
        ListNode temp;
        while (head.next != null) {
            head = head.next;
            temp = new ListNode(head.val);
            temp.next = newHead;
            newHead = temp;
        }
        return newHead;
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
