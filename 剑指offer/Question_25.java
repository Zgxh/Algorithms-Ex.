import java.util.HashMap;

/**
 * 剑指offer第25题：复杂链表的复制
 *
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 *
 * @author Yu Yang
 * @create 2020-02-05 0:36
 */
public class Question_25 {

    /**
     * 思路：遍历两遍，利用hashmap记录地址与index的对应关系。
     * 第一次遍历连接next指针，第二次遍历确定random指向。
     * @param pHead
     * @return
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        HashMap<RandomListNode, Integer> origin = new HashMap<>();
        HashMap<Integer, RandomListNode> copy = new HashMap<>();
        RandomListNode result = new RandomListNode(-1);
        RandomListNode originPointer = pHead;
        RandomListNode copyPointer = result;
        int i = 0;
        while (originPointer != null) {
            origin.put(originPointer, i);
            copyPointer.next = new RandomListNode(originPointer.label);
            copy.put(i++, copyPointer.next);
            originPointer = originPointer.next;
            copyPointer = copyPointer.next;
        }
        originPointer = pHead;
        copyPointer = result.next;
        while (originPointer != null) {
            if (originPointer.random != null) {
                RandomListNode random = copy.get(origin.get(originPointer.random));
                copyPointer.random = random;
            }
            originPointer = originPointer.next;
            copyPointer = copyPointer.next;
        }
        return result.next;
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}
