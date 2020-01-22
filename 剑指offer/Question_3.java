/**
 * 剑指offer第三题：从尾到头打印链表
 *
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 *
 * @author Yu Yang
 * @create 2020-01-22 21:39
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *    public class ListNode {
 *        int val;
 *        ListNode next = null;
 *
 *        ListNode(int val) {
 *            this.val = val;
 *        }
 *    }
 *
 */
public class Question_3 {

    /**
     * 思路1：用栈暂存链表结点，然后依次出栈；
     * 思路2：LinkedList比ArrayList的插入速度快，在频繁插入时，先用LinkedList，
     *       采用头插法建立逆序链表，然后复制为ArrayList。
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        LinkedList<Integer> list = new LinkedList<>();
        if (listNode == null) {
            return new ArrayList<>();
        }
        list.add(listNode.val);
        while (listNode.next != null) {
            listNode = listNode.next;
            list.add(0, listNode.val);
        }
        return new ArrayList<>(list);
    }

    class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }
}
