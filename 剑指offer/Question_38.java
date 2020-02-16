import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer第38题：二叉树的深度
 *
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点
 * （含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * @author Yu Yang
 * @create 2020-02-16 17:15
 */
public class Question_38 {

    /**
     * 思路1：递归法遍历二叉树，每递归一层深度+1；
     * 思路2：非递归法，采用层次遍历，每遍历一层，深度+1；
     */

    /**
     * 递归实现
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 非递归的层次遍历法实现
     * @param root
     * @return
     */
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int nodeOfLevel = 1; // 当前层所有结点数
        int count = 0; // 当前层已遍历过的结点数
        int level = 0; // 已经统计过的层数
        while (!queue.isEmpty()) {
            TreeNode head = queue.poll();
            count++;
            if (head.left != null) {
                queue.add(head.left);
            }
            if (head.right != null) {
                queue.add(head.right);
            }
            if (count == nodeOfLevel) { // 每当遍历完一层，重设状态，并层数+1
                count = 0;
                nodeOfLevel = queue.size();
                level++;
            }
        }
        return level;
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
