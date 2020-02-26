import java.util.HashSet;
import java.util.Set;

/**
 * 剑指offer第55题：链表中环的入口结点
 *
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 * @author Yu Yang
 * @create 2020-02-26 17:44
 */
public class Question_55 {

    /**
     * 思路1：使用HashSet存放已经经过的结点，判断下一个点是否在set内。
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop1(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        set.add(pHead);
        while (pHead.next != null) {
            if (set.contains(pHead.next)) {
                return pHead.next;
            }
            set.add(pHead.next);
            pHead = pHead.next;
        }
        return null;
    }

    /**
     * 思路2：快慢指针，快指针一次走2格，慢指针一次走1格，快慢指针速度之比为2。
     * 如果有环则快慢指针最终会相遇，相遇时快指针走过的路程是慢指针的2倍。根据2倍关系，
     * 可得到起点到环入口的距离与相遇点到环入口的距离相等。让快指针回到起点，与慢指针同时走，
     * 一次走1格，则二者相遇处就是环入口。
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode fast = pHead, slow = pHead;
        while (fast != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                fast = pHead;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
