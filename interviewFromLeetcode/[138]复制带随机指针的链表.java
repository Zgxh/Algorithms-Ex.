//给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。 
//
// 要求返回这个链表的 深拷贝。 
//
// 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示： 
//
// 
// val：一个表示 Node.val 的整数。 
// random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为 null 。 
// 
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
// Related Topics 哈希表 链表


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

import java.util.HashMap;

class Solution {

    /**
     * 思路：先按next的顺序遍历一遍原链表，并复制一份到新链表；
     * 同时用一个hashmap记录下原链表与新链表结点之间的地址值对应关系。
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {

        Map<Node, Node> map = new HashMap<>();
        Node result = new Node(-1);
        Node pointer1 = result;
        Node pointer2 = head;

        while (pointer2 != null) { // 遍历第一遍建立next连接结构，并找到对应关系
            Node temp = new Node(pointer2.val);
            pointer1.next = temp;
            map.put(pointer2, temp);
            pointer2 = pointer2.next;
            pointer1 = pointer1.next;
        }

        pointer1 = result.next;
        while (head != null) { // 遍历第二遍建立random的连接关系
            pointer1.random = map.get(head.random);
            pointer1 = pointer1.next;
            head = head.next;
        }

        return result.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
