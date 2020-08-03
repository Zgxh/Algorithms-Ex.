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
     * 递归写法。
     * @param root
     * @return
     */
     public boolean isSymmetric(TreeNode root) {
         if (root == null) {
             return true;
         }
         return recursion(root.left, root.right);
     }

     private boolean recursion(TreeNode left, TreeNode right) {
         if (left == null && right == null) {
             return true;
         } else if (left == null || right == null || left.val != right.val) {
             return false;
         } else {
             return recursion(left.left, right.right) && recursion(left.right, right.left);
         }
     }
}
//leetcode submit region end(Prohibit modification and deletion)
