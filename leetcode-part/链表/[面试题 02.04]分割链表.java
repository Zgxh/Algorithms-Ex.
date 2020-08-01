//编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。
//分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。 
//
// 示例: 
//
// 输入: head = 3->5->8->5->10->2->1, x = 5
//输出: 3->1->2->10->5->5->8
// 
// Related Topics 链表 双指针 
// 👍 23 👎 0


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
     * 链表改指针问题。
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode fake = new ListNode(-1);
        fake.next = head;
        ListNode lastLess = fake, firstGreater = fake, nextLess = fake;
        ListNode traversalPointer = fake;
        while (traversalPointer.next != null && traversalPointer.next.val < x) { // 找到最后一个小于x的位置
            traversalPointer = traversalPointer.next;
        }
        lastLess = traversalPointer;
        firstGreater = traversalPointer.next; // 大于等于x的最左边的指针位置
        while (traversalPointer.next != null) {
            if (traversalPointer.next.val >= x) {
                traversalPointer = traversalPointer.next;
            } else {
                nextLess = traversalPointer.next;
                traversalPointer.next = nextLess.next;
                lastLess.next = nextLess;
                lastLess.next.next = firstGreater;
                lastLess = lastLess.next;
            }
        }

        return fake.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
