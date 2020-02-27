import java.util.*;

/**
 * 剑指offer第59题：按之字形顺序打印二叉树
 *
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 *
 * @author Yu Yang
 * @create 2020-02-27 9:18
 */
public class Question_59 {

    /**
     * 思路：使用层次遍历，设一个flag，每完事一层，换方向。
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        int count = 0, levelNum = 1;
        boolean leftToRight = true;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> curLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            curLevel.add(temp.val);
            count++;
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
            if (count == levelNum) {
                count = 0;
                levelNum = queue.size();
                if (!leftToRight) { // 到从右向左了
                    Collections.reverse(curLevel);
                }
                result.add(new ArrayList<>(curLevel));
                curLevel.clear();
                leftToRight = !leftToRight;
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
