/*
 * @lc app=leetcode.cn id=102 lang=java
 *
 * [102] 二叉树的层次遍历
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**二叉树层次遍历的递归实现 */
// class Solution {
//     List<List<Integer>> result = new ArrayList<List<Integer>>();
//     public List<List<Integer>> levelOrder(TreeNode root) {
//         if (root == null) {
//             return result;
//         }
//         recursion(root, 0);
//         return result;
//     }
//     public void recursion(TreeNode node, int level) {
//         // 判断存放第i层结点值的链表是否已经建立
//         if (result.size() == level) {
//             result.add(new ArrayList<Integer>());
//         }
//         // 在存放当前层结点的链表中加入当前遍历的结点值
//         result.get(level).add(node.val);
//         // 依次递归遍历左右子树
//         if (node.left != null) {
//             recursion(node.left, level + 1);
//         }
//         if (node.right != null) {
//             recursion(node.right, level + 1);
//         }
//     }
// }

/**二叉树层次遍历的迭代实现，使用队列 */
class Solution {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return result;
        }
        // 采用队列来依次放入当前层的结点，具体实现用 LinkedList
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            result.add(new ArrayList<Integer>());
            int current_level_length = queue.size();
            for (int i = 0; i < current_level_length; i++) {
                TreeNode current = queue.poll();
                result.get(level).add(current.val);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            level++;
        }
        return result;
    }
}

