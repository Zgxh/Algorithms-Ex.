import java.util.LinkedList;

/**
 * 剑指offer第18题：二叉树的镜像
 *
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * @author Yu Yang
 * @create 2020-01-31 20:39
 */
public class Question_18 {

    /**
     * 思路：利用队列进行二叉树的层次遍历。过程中交换结点的左右孩子。
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode queueHead = queue.poll();
            // swap left and right
            TreeNode temp = queueHead.left;
            queueHead.left = queueHead.right;
            queueHead.right = temp;
            if (queueHead.left != null) {
                queue.offer(queueHead.left);
            }
            if (queueHead.right != null) {
                queue.offer(queueHead.right);
            }
        }
    }

    /**
     * Definition of tree node.
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
