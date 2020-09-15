//请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指
//向链表中的任意节点或者 null。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
//
// 示例 4： 
//
// 输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
// 
//
// 
//
// 提示： 
//
// 
// -10000 <= Node.val <= 10000 
// Node.random 为空（null）或指向链表中的节点。 
// 节点数目不超过 1000 。 
// 
//
// 
//
// 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-point
//er/ 
//
// 
// Related Topics 链表 
// 👍 87 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {

    /**
     * 链表的深拷贝问题。
     * 解法1：哈希表法。简单。
     * 解法2：链表连接法。把复制的结点紧跟在原结点后面，即next指针指向新复制的
     * 结点；第二次遍历连接random指针；第三次遍历分离原链表和深拷贝的链表。
     * @param head
     * @return
     */

    // 哈希表写法
    // public Node copyRandomList(Node head) {
    //     Map<Node, Node> map = new HashMap();
    //     Node pointer = head;
    //     Node dummy = new Node(-1);
    //     Node pointer2 = dummy;
    //     while (pointer != null) {
    //         pointer2.next = new Node(pointer.val);
    //         map.put(pointer, pointer2.next);
    //         pointer = pointer.next;
    //         pointer2 = pointer2.next;
    //     }
    //     pointer = head;
    //     pointer2 = dummy.next;
    //     while (pointer != null) {
    //         pointer2.random = map.get(pointer.random);
    //         pointer = pointer.next;
    //         pointer2 = pointer2.next;
    //     }

    //     return dummy.next;
    // }

    // 另一种写法，非哈希表
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        deepCopy(head);
        linkRandomPointer(head);

        return reconstructList(head);
    }

    private void deepCopy(Node head) {
        Node pointer = head;
        while (pointer != null) {
            Node copy = new Node(pointer.val);
            Node next = pointer.next;
            copy.next = next;
            pointer.next = copy;
            pointer = next;
        }
    }

    private void linkRandomPointer(Node head) {
        Node pointer = head;
        while (pointer != null) {
            Node copy = pointer.next;
            if (pointer.random != null) {
                copy.random = pointer.random.next;
            }
            pointer = copy.next;
        }
    }

    private Node reconstructList(Node head) {
        Node pointer = head;
        Node copyHead = head.next;
        Node copyPointer = copyHead;
        while (pointer != null) {
            pointer.next = copyPointer.next;
            pointer = pointer.next;
            if (pointer != null) {
                copyPointer.next = pointer.next;
                copyPointer = copyPointer.next;
            }
        }

        return copyHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
