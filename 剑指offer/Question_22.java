import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 剑指offer第22题：从上往下打印二叉树
 *
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 * @author Yu Yang
 * @create 2020-02-01 21:14
 */
public class Question_22 {

    /**
     * 思路：借助队列实现二叉树的层次遍历
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            result.add(temp.val);
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return result;
    }

    /**
     * 二叉树结点定义
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
