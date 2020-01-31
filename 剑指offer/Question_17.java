/**
 * 剑指offer第17题：树的子结构
 *
 * 输入两棵二叉树A，B，判断B是不是A的子结构。
 * （ps：我们约定空树不是任意一个树的子结构）
 *
 * @author Yu Yang
 * @create 2020-01-31 17:44
 */
public class Question_17 {

    /**
     * 思路：子结构不一定是子树。先找到val相同的结点，然后往下继续判断子树是否满足条件。
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return judgeSubtree(root1, root2) ||
                judgeSubtree(root1.left, root2) || //后两种情况避免root1=root2但子树不同
                judgeSubtree(root1.right, root2);
    }

    public boolean judgeSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) { // root2的判断必须在root1的前边，保证子结构概念！
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return judgeSubtree(root1.left, root2) || judgeSubtree(root1.right, root2);
        }
        return judgeSubtree(root1.left, root2.left) && judgeSubtree(root1.right, root2.right);
    }

    /**
     * Definition of tree node
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
