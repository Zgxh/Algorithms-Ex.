import java.util.LinkedList;

/**
 * 剑指offer第62题：二叉搜索树的第k个结点
 *
 * 给定一棵二叉搜索树，请找出其中的第 k 小的结点。
 * 例如，（5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 *
 * @author Yu Yang
 * @create 2020-03-01 20:29
 */
public class Question_62 {

    /**
     * 思路：中序遍历的第k个结点,非递归实现
     * @param pRoot
     * @param k
     * @return
     */
    public TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k < 0) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        int index = 0;
        TreeNode node = pRoot;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                index++;
                if (index == k) {
                    return stack.element();
                }
                node = stack.pop().right;
            }
        }
        return null;
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
