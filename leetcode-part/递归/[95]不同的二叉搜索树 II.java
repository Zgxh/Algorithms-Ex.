//给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。 
//
// 
//
// 示例： 
//
// 输入：3
//输出：
//[
//  [1,null,3,2],
//  [3,2,null,1],
//  [3,1,null,null,2],
//  [2,1,3],
//  [1,null,2,null,3]
//]
//解释：
//以上的输出对应以下 5 种不同结构的二叉搜索树：
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 8 
// 
// Related Topics 树 动态规划 
// 👍 663 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 递归法。
     * 二叉排序树左子树必然小于右子树，遍历所有可能的根，
     * 并递归地进行左右子树的建立。
     * 其中使用了HashMap存放中间值，避免重复计算。
     */

    private Map<String, List<TreeNode>> cache; // 缓存，提前退出递归

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList();
        }
        cache = new HashMap();

        return generateTreesHelp(1, n);
    }

    private List<TreeNode> generateTreesHelp(int start, int end) {
        List<TreeNode> result = new ArrayList();
        if (start > end) {
            result.add(null);
            return result;
        } else if (start == end) {
            result.add(new TreeNode(start));
            return result;
        }
        // 枚举所有可能的根，并依次生成所有可能的树
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = cache.get(start + "#" + (i - 1));
            if (leftTrees == null) {
                leftTrees = generateTreesHelp(start, i - 1);
                cache.put(start + "#" + (i - 1), leftTrees);
            }
            List<TreeNode> rightTrees = cache.get(i + 1 + "#" + end);
            if (rightTrees == null) {
                rightTrees = generateTreesHelp(i + 1, end);
                cache.put(i + 1 + "#" + end, rightTrees);
            }
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode subtreeRoot = new TreeNode(i);
                    subtreeRoot.left = leftTree;
                    subtreeRoot.right = rightTree;
                    result.add(subtreeRoot);
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
