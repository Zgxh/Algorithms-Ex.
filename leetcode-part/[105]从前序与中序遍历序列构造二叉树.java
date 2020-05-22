//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    /**
     * 思路：根据前序遍历与中序遍历的遍历顺序来划分。
     * 前序遍历顺序：根结点 -> 左子树 -> 右子树；
     * 中序遍历顺序：左子树 -> 根结点 -> 右子树；
     * 通过这顺序，可以得出：从preorder找到根，然后在中序中找根并划分左右子树，可以得到左右子树的结点数。
     * 递归下去，建立子树并依次连接。
     */

    private Map<Integer, Integer> map; // 存放inorder结果的映射关系：值 -> index
    private int[] preorder;
    private int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        this.preorder = preorder;
        this.inorder = inorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTreeHelp(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelp(int leftOfPreorder, int rightOfPreorder, int leftOfInorder, int rightOfInorder) {

        if (leftOfPreorder > rightOfPreorder) {
            return null;
        }

        int rootOfPreorder = leftOfPreorder; // 先序遍历子树的根
        int rootOfInorder = map.get(preorder[rootOfPreorder]); // 中序遍历子树的根
        int sizeOfLeftSubtree = rootOfInorder - leftOfInorder; // 左子树的结点个数

        TreeNode root = new TreeNode(preorder[rootOfPreorder]);
        // 递归构建左子树
        root.left = buildTreeHelp(leftOfPreorder + 1, leftOfPreorder + sizeOfLeftSubtree, leftOfInorder, rootOfInorder - 1);
        // 递归构建右子树
        root.right = buildTreeHelp(leftOfPreorder + sizeOfLeftSubtree + 1, rightOfPreorder, rootOfInorder + 1, rightOfInorder);

        return root;
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}
//leetcode submit region end(Prohibit modification and deletion)
