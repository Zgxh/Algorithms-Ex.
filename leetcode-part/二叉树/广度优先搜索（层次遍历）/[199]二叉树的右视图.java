//给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。 
//
// 示例: 
//
// 输入: [1,2,3,null,5,null,4]
//输出: [1, 3, 4]
//解释:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
// 
// Related Topics 树 深度优先搜索 广度优先搜索



//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
     * 思路：二叉树的层次遍历 （广度优先搜索）
     * 每次到当前层最右侧，则添加记录即可。
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int nodePerLayer = 1, curNode = 0;
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.removeLast();
            curNode++;
            if (temp.left != null) {
                queue.addFirst(temp.left);
            }
            if (temp.right != null) {
                queue.addFirst(temp.right);
            }
            if (curNode == nodePerLayer) { // 当前层结束
                curNode = 0;
                nodePerLayer = queue.size();
                result.add(temp.val);
            }
        }


        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
