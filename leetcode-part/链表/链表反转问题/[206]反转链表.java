//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1200 👎 0


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
    // 迭代写法
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode result = head;
        ListNode pointer = head;
        while (pointer.next != null) {
            ListNode temp = pointer.next;
            pointer.next = pointer.next.next;
            temp.next = result;
            result = temp;
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
