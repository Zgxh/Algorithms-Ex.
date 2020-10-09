//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。 
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。 
//
// 示例 1: 
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7. 
//
// 示例 2: 
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
// 
// Related Topics 树 深度优先搜索 
// 👍 597 👎 0


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
    // 递归+分治
    public int rob(TreeNode root) {
        int[] result = robHelp(root);

        return Math.max(result[0], result[1]);
    }

    // 分为两种情况，一种是root被选择，一种是root不被选择
    private int[] robHelp(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] robLeft = robHelp(root.left);
        int[] robRight = robHelp(root.right);
        int select = root.val + robLeft[1] + robRight[1];
        int notSelect = Math.max(robLeft[0], robLeft[1]) + Math.max(robRight[0], robRight[1]);

        return new int[]{select, notSelect};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
