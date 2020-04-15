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
     * 题目要求常数级空间，说明不能用递归的方法；又要求时间复杂度为O(nlogn),
     * 所以要用 [自底向上] 的归并排序。
     * 具体过程下面注释写的很清楚。
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {

        // 确定链表长度
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 归并排序
        for (int m = 1; m < len; m *= 2) {
            ListNode pre = dummy;
            cur = pre.next;

            // 每次取出连续的两部分进行merge
            while (cur != null) {
                ListNode first = cur; // 第一块的指针
                for (int i = 0; i < m - 1 && cur != null; i++) { // 取m个元素作为第一个子序列
                    cur = cur.next;
                }
                if (cur == null) { // 单独的序列不用归并，直接晋升到下一级
                    break;
                }
                ListNode second = cur.next; // 第二块的指针
                cur.next = null; // 断开两个子序列的连接
                cur = second;
                for (int i = 0; i < m - 1 && cur != null; i++) { // 取m个元素作为第二个子序列
                    cur = cur.next;
                }

                // 断开第二部分与剩余部分的连接，并留下剩余部分的指针
                ListNode remain = null;
                if (cur != null) {
                    remain = cur.next;
                    cur.next = null;
                }
                cur = remain;

                // 对first和second所引导的子序列进行merge
                ListNode tmp = merge(first, second);

                // 归并完的序列连到原序列上
                pre.next = tmp;
                while (pre.next != null) {
                    pre = pre.next;
                }
                pre.next = remain; // 把剩余的部分连到后面，这句话是必要的，防止下次分割只出现一个序列导致下个归并的循环那部分丢失
            }
        }

        return dummy.next;
    }

    /**
     * 归并两个链表子序列
     * @param a
     * @param b
     * @return 归并后的新链表头结点
     */
    private ListNode merge(ListNode a, ListNode b) {

        ListNode pre = new ListNode(0);
        ListNode cur = pre;

        while (a != null && b != null ) { // 归并
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }

        // 把剩下的部分连上
        if (a != null) {
            cur.next = a;
        }
        if (b != null) {
            cur.next = b;
        }

        return pre.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
