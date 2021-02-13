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
// 👍 255 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;

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
     * 写法1：推荐
     * 根据后序遍历的特点，复现后序遍历的过程，并用 postorderRootIndex 来记录
     * 后序遍历结点的位置。
     * 后序遍历的特点是：在访问该结点前，总是先访问该节点的右子树的最右结点。
     */

    private int[] inorder;
    private int[] postorder;
    private int postorderRootIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        this.postorderRootIndex = postorder.length - 1;

        return buildTreeHelp(0, inorder.length - 1);
    }

    private TreeNode buildTreeHelp(int left, int right) {
        if (left > right || postorderRootIndex < 0) {
            return null;
        }
        int rootValue = postorder[postorderRootIndex];
        TreeNode root = new TreeNode(rootValue);
        // 从中序遍历序列中找到子树的根，并以此划分左右子树
        int inorderRootIndex = findInorderRoot(left, right, rootValue);
        postorderRootIndex--;
        // 递归构造右子树
        root.right = buildTreeHelp(inorderRootIndex + 1, right);
        // 递归构造左子树
        root.left = buildTreeHelp(left, inorderRootIndex - 1);

        return root;
    }

    private int findInorderRoot(int left, int right, int target) {
        while (left < right) {
            if (inorder[left] == target) {
                return left;
            }
            left++;
        }

        return left;
    }

//    /**
//     * 写法2.
//     */
//    private int[] inorder;
//    private int[] postorder;
//
//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        if (inorder.length == 0) {
//            return null;
//        }
//        this.inorder = inorder;
//        this.postorder = postorder;
//        TreeNode root = buildTreeHelp(0, inorder.length - 1, 0, postorder.length - 1);
//
//        return root;
//    }
//
//    private TreeNode buildTreeHelp(int inLeft, int inRight, int postLeft, int postRight) {
//        // if (inLeft < inRight) {
//        //     return null;
//        // }
//        if (inLeft == inRight) {
//            return new TreeNode(inorder[inLeft]);
//        }
//        int inorderRootIndex = findInorderRoot(inLeft, inRight, postorder[postRight]);
//        TreeNode root = new TreeNode(postorder[postRight]); // root
//        if (inorderRootIndex > inLeft) {
//            root.left = buildTreeHelp(inLeft, inorderRootIndex - 1, postLeft, postLeft + inorderRootIndex - 1 - inLeft);
//        }
//        if (inorderRootIndex < inRight) {
//            root.right = buildTreeHelp(inorderRootIndex + 1, inRight, postRight - 1 - (inRight - inorderRootIndex - 1), postRight - 1);
//        }
//
//        return root;
//    }
//
//    private int findInorderRoot(int left, int right, int target) {
//        for (int i = left; i <= right; i++) {
//            if (inorder[i] == target) {
//                return i;
//            }
//        }
//        return left;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
