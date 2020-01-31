/**
 * 剑指offer第16题：合并两个排序的链表
 *
 * 输入两个单调递增的链表，输出两个链表合成后的链表，
 * 当然我们需要合成后的链表满足单调不减规则。
 *
 * @author Yu Yang
 * @create 2020-01-31 17:34
 */
public class Question_16 {

    /**
     * 思路：似于归并排序中的归并操作，改成了链表。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode result = new ListNode(-1);
        ListNode tail = result;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                tail.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 == null) {
            tail.next = list2;
        } else {
            tail.next = list1;
        }
        return result.next;
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
