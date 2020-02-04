
import java.util.LinkedList;

/**
 * 剑指offer第26题：二叉搜索树与双向链表
 *
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * @author Yu Yang
 * @create 2020-02-05 1:09
 */
public class Question_26 {

    /**
     * 思路：二叉搜索树中序遍历得到排序结果，存起来，然后改指针。
     * （中序遍历使用的是非递归写法）
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>(); // 中序遍历的辅助
        LinkedList<TreeNode> stack1 = new LinkedList<>(); // 储存中序遍历后排序结果
        TreeNode node = pRootOfTree;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode temp = stack.pop();
                stack1.push(temp);
                node = temp.right;
            }
        }
        TreeNode last = stack1.pop();
        node = last; // 防止只有一个结点的情况
        while (!stack1.isEmpty()) {
            node = stack1.pop();
            last.left = node;
            node.right = last;
            last = node;
        }
        return node;
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
