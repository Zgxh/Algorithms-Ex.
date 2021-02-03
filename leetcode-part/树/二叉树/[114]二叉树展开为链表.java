//给定一个二叉树，原地将它展开为一个单链表。 
//
// 
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
// Related Topics 树 深度优先搜索 
// 👍 446 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;

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

    /**
     * 思路：找前驱结点。
     * 题目意思是按前序遍历的顺序来拼接链表，而且是向右偏向。
     * 当前结点A的右子结点R的前驱结点即是当前结点A的左子树的最右结点R1，
     * 步骤：若当前结点的左子树不空，则把当前结点的右子结点赋给当前结点的左子树的最右节点，
     * 然后把当前结点的左子树赋给当前结点的右子结点。然后指针往右子结点走。
     *
     * 时间复杂度：O(n),空间复杂度 O(1)
     */
    public void flatten(TreeNode root) {
        TreeNode pointer = root;
        while (pointer != null) {
            if (pointer.left != null ) {
                if (pointer.right != null) {
                    TreeNode leftRight = findRightInLeft(pointer.left);
                    leftRight.right = pointer.right;
                }
                pointer.right = pointer.left;
                pointer.left = null;
            }
            pointer = pointer.right;
        }
    }

    private TreeNode findRightInLeft(TreeNode left) {
        while (left.right != null) {
            left = left.right;
        }

        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
