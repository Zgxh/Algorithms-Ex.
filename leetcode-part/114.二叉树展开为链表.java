
/*
 * @lc app=leetcode.cn id=114 lang=java
 *
 * [114] 二叉树展开为链表
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
 * 思路：二叉树的前序遍历，顺序：根-左-右。下到最底，一层一层回溯，第二次回到根时，其左子树已经是flatten好的。所以不断把当前节点的右子树添加到当前节点的左子树的最后一个节点上（左子树的最下一层右节点），然后把右子树替换为左子树，左子树[置空]。
 * 
 */
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) 
            return;
        if (root.left != null) {
            flatten(root.left);
        }
        if (root.right != null) {
            flatten(root.right);
        }
        if (root.left != null) {
            TreeNode tmp = root.left;
            while (tmp.right != null) 
                tmp = tmp.right;
            tmp.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }
}

