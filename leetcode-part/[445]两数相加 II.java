//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 进阶： 
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。 
//
// 
//
// 示例： 
//
// 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 8 -> 0 -> 7
// 
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

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
     * 思路：利用栈。把两个链表都压入栈，然后依次弹出求和，注意进位就可。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<Integer> stack1 = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.addFirst(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.addFirst(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode result = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int current = stack1.removeFirst() + stack2.removeFirst() + carry;
            carry = current / 10;
            current = current % 10;
            ListNode temp = new ListNode(current);
            temp.next = result;
            result = temp;
        }
        while (!stack1.isEmpty()) {
            int current = stack1.removeFirst() + carry;
            carry = current / 10;
            current = current % 10;
            ListNode temp = new ListNode(current);
            temp.next = result;
            result = temp;
        }
        while (!stack2.isEmpty()) {
            int current = stack2.removeFirst() + carry;
            carry = current / 10;
            current = current % 10;
            ListNode temp = new ListNode(current);
            temp.next = result;
            result = temp;
        }
        if (carry > 0) {
            ListNode temp = new ListNode(carry);
            temp.next = result;
            result = temp;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
