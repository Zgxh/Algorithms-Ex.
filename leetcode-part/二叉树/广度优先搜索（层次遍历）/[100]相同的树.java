//给定两个二叉树，编写一个函数来检验它们是否相同。 
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 示例 1: 
//
// 输入:       1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
//输出: true 
//
// 示例 2: 
//
// 输入:      1          1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
//输出: false
// 
//
// 示例 3: 
//
// 输入:       1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
//输出: false
// 
// Related Topics 树 深度优先搜索 
// 👍 417 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 广度优先搜索
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList();
        Queue<TreeNode> queue2 = new LinkedList();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode temp1 = queue1.poll();
            TreeNode temp2 = queue2.poll();
            if (temp1.val != temp2.val || ((temp1.left == null) ^ (temp2.left == null))
                    || ((temp1.right == null) ^ (temp2.right == null))) { // 两个结点的值不同，或者两个左孩子（右孩子）中仅有一个为空，则结构不同
                return false;
            }
            if (temp1.left != null) { // 过了上面一关，则其中一个左孩子不空就说明两个左孩子都不空
                queue1.offer(temp1.left);
                queue2.offer(temp2.left);
            }
            if (temp1.right != null) {
                queue1.offer(temp1.right);
                queue2.offer(temp2.right);
            }
        }

        return queue1.isEmpty() && queue2.isEmpty(); // 判断两个队列最后是否都变空了，避免其中一个子树还存在其他结点
    }
}
//leetcode submit region end(Prohibit modification and deletion)
