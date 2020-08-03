//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 939 👎 0


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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 利用队列进行伪层次遍历.
     * 队列中每次加入相邻的两个需要被比较的结点。开始时加入两个根节点。
     */

    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            } else if (left == null || right == null || left.val != right.val) {
                return false;
            } else {
                queue.offer(left.left);
                queue.offer(right.right);
                queue.offer(left.right);
                queue.offer(right.left);
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
