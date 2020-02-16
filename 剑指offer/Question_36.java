/**
 * 剑指offer第36题：两个链表的第一个公共结点
 *
 * 输入两个链表，找出它们的第一个公共结点。
 * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 *
 * @author Yu Yang
 * @create 2020-02-16 15:05
 */
public class Question_36 {

    /**
     * 根据单链表的特点，题目中的两个单链表一定会形成一个Y形的结构，即从某个结点开始，后面的结点一模一样。
     * 思路1：分别遍历两个链表，依次压栈，然后分别依次弹出，最后两个相同的结点就是结果。时间O(m+n)，空间O(m+n)
     * 思路2：分别遍历两个链表，求出长度差值L。然后双指针，长链表先走L步，然后两个指针一块走，
     *        直到遇到第一个相同结点。时间O(m+n),且不需要额外空间
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        int length1 = 0, length2 = 0;
        ListNode pointer1 = pHead1, pointer2 = pHead2;
        // 判断长度
        while (pointer1 != null) {
            length1++;
            pointer1 = pointer1.next;
        }
        while (pointer2 != null) {
            length2++;
            pointer2 = pointer2.next;
        }
        int deltaL = length1 >= length2 ? length1 - length2 : length2 - length1;
        if (length1 < length2) {
            ListNode temp = pHead1;
            pHead1 = pHead2;
            pHead2 = temp;
        }
        while (deltaL > 0) {
            pHead1 = pHead1.next;
            deltaL--;
        }
        while (pHead1 != null && pHead2!= null) { // 双指针找交点
            if (pHead1 == pHead2) {
                return pHead1;
            }
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return null;
    }

    /**
     * 结点定义
     */
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
