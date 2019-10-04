import java.util.*;


/*
 * @lc app=leetcode.cn id=112 lang=java
 *
 * [112] 路径总和
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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        List<TreeNode> stack = new ArrayList<>();
        int current = 0;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.add(root);
                current += root.val;
                if (root.left == null && root.right == null) {
                    if (current == sum) {
                        return true;
                    }
                }
                root = root.left;
            }
            else {
                root = stack.get(stack.size() - 1);
                if (root.right == null) {
                    stack.remove(stack.size() - 1);
                    if (stack.isEmpty()) {
                        break;
                    }
                    root = stack.get(stack.size() - 1).right;
                }
                else {
                    root = root.right;
                }
            }
        }
        return false;
    }
    // public int getSum(List<TreeNode> stack) {
    //     int sum = 0;
    //     for (TreeNode node : stack) {
    //         sum += node.val;
    //     }
    //     return sum;
    // }
}

