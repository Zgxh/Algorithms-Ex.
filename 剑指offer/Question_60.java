import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer第60题：把二叉树打印成多行
 *
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 *
 * @author Yu Yang
 * @create 2020-03-01 19:12
 */
public class Question_60 {

    /**
     * 思路：二叉树的层次遍历，设置levelNum记录当前层的结点总数，count记录已经遍历过的当前层的结点数。
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        int levelNum = 1;
        int count = 0;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> curLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            count++;
            curLevel.add(temp.val);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
            if (count == levelNum) {
                levelNum = queue.size();
                count = 0;
                result.add(new ArrayList<>(curLevel));
                curLevel.clear();
            }
        }
        return result;
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
