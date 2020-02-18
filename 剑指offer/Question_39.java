/**
 * 剑指offer第39题：平衡二叉树
 *
 * 输入一棵二叉树，判断该二叉树是否为平衡二叉树。这里的平衡二叉树不要求是二叉排序树。
 *
 * @author Yu Yang
 * @create 2020-02-18 12:47
 */
public class Question_39 {

    /**
     * 平衡二叉树的特点：任意结点的左右子树高度之差不大于1.
     * 思路：根据这个特点，递归求左右子树的高度并进行判断。
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        return depth(root) != -1;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depthOfLeft = depth(root.left);
        if (depthOfLeft == -1) {
            return -1;
        }
        int depthOfRight = depth(root.right);
        if (depthOfRight == -1) {
            return -1;
        }
        return Math.abs(depthOfLeft - depthOfRight) > 1 ? -1 : 1 + Math.max(depthOfLeft, depthOfRight);
    }

    /**
     * Definition of binary tree node.
     */
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
