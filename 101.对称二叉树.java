/*
 * @lc app=leetcode.cn id=101 lang=java
 *
 * [101] 对称二叉树
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
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return partIsSymmetric(root.left, root.right);
    }
    public boolean partIsSymmetric(TreeNode tree1, TreeNode tree2) {
        /*
         * 思想：递归。
         * 1.先判断是否都为叶子结点
         * 2.不是叶子结点则继续递归子树
         */
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        return tree1.val == tree2.val && partIsSymmetric(tree1.left, tree2.right) && partIsSymmetric(tree1.right, tree2.left);
    }
}

