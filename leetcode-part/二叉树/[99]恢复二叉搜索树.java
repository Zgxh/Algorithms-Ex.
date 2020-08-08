//二叉搜索树中的两个节点被错误地交换。 
//
// 请在不改变其结构的情况下，恢复这棵树。 
//
// 示例 1: 
//
// 输入: [1,3,null,null,2]
//
//   1
//  /
// 3
//  \
//   2
//
//输出: [3,1,null,null,2]
//
//   3
//  /
// 1
//  \
//   2
// 
//
// 示例 2: 
//
// 输入: [3,1,4,null,null,2]
//
//  3
// / \
//1   4
//   /
//  2
//
//输出: [2,1,4,null,null,3]
//
//  2
// / \
//1   4
//   /
//  3 
//
// 进阶: 
//
// 
// 使用 O(n) 空间复杂度的解法很容易实现。 
// 你能想出一个只使用常数空间的解决方案吗？ 
// 
// Related Topics 树 深度优先搜索 
// 👍 267 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

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
     * 思路：中序遍历
     * 二叉搜索树的中序遍历是递增序的，所以通过中序遍历可以找到逆序对的位置。
     * 存在两种情况：
     * 1. 只有一对逆序对，说明中序遍历相邻的两点被交换了；
     * 2. 两对逆序对，说明第一个逆序对的左节点与第二个逆序对的右节点被交换了；
     * 所以在中序遍历的过程中，记录上一个中序遍历的结点，然后每次与上一个被遍历的
     * 结点值进行比较，找到第一个逆序对的位置并进行记录，然后接着遍历找第二个逆序对，
     * 若找到了第二个逆序对，则把第一个逆序对的左节点与第二个逆序对的右节点进行交换；
     * 若到最后也没找到第二个逆序对，则把第一个逆序对的左右节点值交换。
     *
     * 时间复杂度 O(n), 空间复杂度 O(depth)，与二叉搜索树的高度有关
     * @param root
     */
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList();
        TreeNode pointer = root; // 中序遍历树的指针
        TreeNode firstLeft = null, firstRight = null; // 第一个逆序对的位置
        TreeNode lastNode = null; // 上一个中序遍历的结点
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            } else {
                TreeNode temp = stack.pop();
                if (lastNode != null) {
                    if (lastNode.val > temp.val) {
                        if (firstLeft != null) {
                            int tempVal = firstLeft.val;
                            firstLeft.val = temp.val;
                            temp.val = tempVal;
                            return;
                        } else {
                            firstLeft = lastNode;
                            firstRight = temp;
                        }
                    }
                }
                lastNode = temp;
                pointer = temp.right;
            }
        }
        int tempVal = firstLeft.val;
        firstLeft.val = firstRight.val;
        firstRight.val = tempVal;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
