/**
 * 剑指offer第56题：删除链表中重复的结点
 *
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，
 * 返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 * @author Yu Yang
 * @create 2020-02-26 17:44
 */
public class Question_56 {

    /**
     * 思路：判断下个结点与下下个结点值是否相同，如果相同则跳过这俩结点，并记录结点值（为了防止出现奇数次相同值）。
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode head = new ListNode(-1);
        head.next = pHead;
        ListNode pointer = head;
        int temp = -1;
        while (pointer.next != null) {
            if (pointer.next.next != null && pointer.next.val == pointer.next.next.val) { // 两个相邻结点重复
                temp = pointer.next.val;
                pointer.next = pointer.next.next.next;
            } else if (temp == pointer.next.val) { // 多余出来的第奇数个结点重复
                pointer.next = pointer.next.next;
            } else { // 无事发生
                pointer = pointer.next;
            }
        }
        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
