//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
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
     * 思路：与105题类似，根据中序遍历与后序遍历的访问顺序，确定根结点与左右子树的位置，
     * 然后递归建立并连接左右子树与根结点。
     */

    private Map<Integer, Integer> map;
    private int[] postorder;
    private int[] inorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        this.postorder = postorder;
        this.inorder = inorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTreeHelp(0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTreeHelp(int leftOfInorder, int rightOfInorder, int leftOfPostorder, int rightOfPostorder) {
        if (leftOfPostorder > rightOfPostorder) {
            return null;
        }

        int rootOfPostorder = rightOfPostorder; // 后序遍历的根
        int rootOfInorder = map.get(postorder[rootOfPostorder]); // 中序遍历的根
        int sizeOfRightSubtree = rightOfInorder - rootOfInorder; // 右子树的size

        TreeNode root = new TreeNode(inorder[rootOfInorder]);
        // 递归建立右子树
        root.right = buildTreeHelp(rootOfInorder + 1, rightOfInorder, rightOfPostorder - sizeOfRightSubtree, rightOfPostorder - 1);
        // 递归建立左子树
        root.left = buildTreeHelp(leftOfInorder, rootOfInorder - 1, leftOfPostorder, rightOfPostorder - sizeOfRightSubtree - 1);

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
