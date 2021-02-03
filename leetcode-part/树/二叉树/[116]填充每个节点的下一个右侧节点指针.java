//给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下： 
//
// struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//} 
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。 
//
// 初始状态下，所有 next 指针都被设置为 NULL。 
//
// 
//
// 示例： 
//
// 
//
// 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"ri
//ght":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right
//":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left"
//:null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":nu
//ll,"next":null,"right":null,"val":7},"val":3},"val":1}
//
//输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4
//","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next"
//:null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":
//null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":
//"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"va
//l":1}
//
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
// 
//
// 
//
// 提示： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
// 
// Related Topics 树 深度优先搜索 
// 👍 216 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

import org.w3c.dom.Node;

class Solution {

    /**
     * 思路1：二叉树的层序遍历。
     * 思路2：空间复杂度为O(1)，因为是完美二叉树，所以用一个指针来
     * 记录一层最左结点的指针，切换下一层只需把指针指向当前结点的左子结点即可。
     * 对于每一层的next指针的连接，分为父节点相同的左右孩子，和，
     * 相邻父节点的右孩子与下个左孩子。
     * @param root
     * @return
     */
    public Node connect(Node root) {
        Node layerPointer = root; // 每层的第一个结点的指针
        while (layerPointer != null) {
            Node curPointer = layerPointer; // 遍历每层结点的指针
            while (curPointer.left != null) {
                curPointer.left.next = curPointer.right; // 同一结点的左右孩子直接相连
                if (curPointer.next != null) {
                    curPointer.right.next = curPointer.next.left; // 相邻结点的相邻左右孩子进行连接
                    curPointer = curPointer.next;
                } else {
                    break;
                }

            }
            layerPointer = layerPointer.left; // 切换下一层
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
