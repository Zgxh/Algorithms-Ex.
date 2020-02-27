/**
 * 剑指offer第58题：对称的二叉树
 *
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 * @author Yu Yang
 * @create 2020-02-27 9:18
 */
public class Question_58 {

    /**
     * 思路：递归写法。
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isSymmetricalHelp(pRoot.left, pRoot.right);
    }

    boolean isSymmetricalHelp(TreeNode leftTree, TreeNode rightTree) {
        if (leftTree == null && rightTree == null) {
            return true;
        }
        if (leftTree == null || rightTree == null) {
            return false;
        }
        if (leftTree.val == rightTree.val) {
            return isSymmetricalHelp(leftTree.left, rightTree.right) &&
                    isSymmetricalHelp(leftTree.right, rightTree.left);
        }
        return false;
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
