//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针


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
     * 先利用快慢指针找到链表中点，断开前后两部分。
     * 把后边部分进行反转，然后依次判断对应位置元素是否相等。
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null && fast.next == null) { // 此时链表结点为奇数个
            slow = slow.next;
        }
        if (slow == null) { // 此时链表只有一个结点
            return true;
        }

        // 之后head引导的就是第一部分，slow引导的就是第二部分
        // 对slow引导的第二部分进行反转
        ListNode dummy = new ListNode(-1);
        while (slow != null) {
            ListNode temp = dummy.next;
            dummy.next = slow;
            slow = slow.next;
            dummy.next.next = temp;
        }

        // 判断是否为回文
        while (dummy.next != null) {
            if (head.val != dummy.next.val) {
                return false;
            }
            dummy = dummy.next;
            head = head.next;
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
