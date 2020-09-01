//在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。 
//
// 示例 1: 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2: 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5 
// Related Topics 排序 链表 
// 👍 712 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /**
     * 递归的归并排序。
     *
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) { // 子链表的长度小于等于 1
            return head;
        }
        ListNode midNode = segmentList(head);
        ListNode secondHead = midNode.next;
        midNode.next = null; // 分割两个子链表
        // 分别对两个子链表进行排序
        ListNode first = sortList(head);
        ListNode second = sortList(secondHead);
        // 合并两个排序后的子链表
        return merge(first, second);
    }

    // 利用快慢指针找到链表的中间结点
    private ListNode segmentList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // ListNode dummy = new ListNode(-1);
        // dummy.next = head;
        // ListNode fast = dummy;
        // ListNode slow = dummy;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 合并两个排序后的子链表
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode result = new ListNode(-1);
        ListNode pointer = result;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                pointer.next = head1;
                head1 = head1.next;
            } else {
                pointer.next = head2;
                head2 = head2.next;
            }
            pointer = pointer.next;
        }
        if (head1 != null) {
            pointer.next = head1;
        } else if (head2 != null) {
            pointer.next = head2;
        }

        return result.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
