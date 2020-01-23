
/**
 * 剑指offer第四题：重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * @author Yu Yang
 * @create 2020-01-22 22:08
 */


import java.util.Arrays;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Question_4 {

    /**
     * 思路：根据前序遍历和中序遍历的特点，从前序遍历序列中找根，用中序遍历来划分左右子树：
     * （1）pre[0]为根节点；
     * （2）在in[]中找到pre[0]，并划分左右子树；
     * （3）左右子树分别递归求解
     *
     * @param pre 前序遍历序列
     * @param in 中序遍历序列
     * @return 重建的二叉树
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode result = new TreeNode(pre[0]); // root node
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                result.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                result.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return result;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
