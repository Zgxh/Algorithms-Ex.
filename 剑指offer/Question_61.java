/**
 * 剑指offer第61题：序列化二叉树
 *
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
 * 从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、
 * 层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过某种
 * 符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 *
 * @author Yu Yang
 * @create 2020-03-01 19:21
 */
public class Question_61 {

    private int index = 0;

    /**
     * 实现的是先序遍历的递归实现
     * @param root
     * @return
     */
    public String Serialize(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        return root.val + "!" + Serialize(root.left) + Serialize(root.right);
    }

    public TreeNode Deserialize(String str) {
        String[] strs = str.split("!");
        return deserializeHelp(strs);
    }

    private TreeNode deserializeHelp(String[] strs) {
        if (strs[index].equals("#")) { // 不要用==，==比较的是引用
            index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(strs[index]));
        index++;
        root.left = deserializeHelp(strs);
        root.right = deserializeHelp(strs);
        return root;
    }

    /**
     * Definition for a binary tree node.
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
