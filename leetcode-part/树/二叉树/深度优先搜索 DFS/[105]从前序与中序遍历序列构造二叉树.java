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
// 👍 657 👎 0


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
     * 写法1。【推荐】
     * 根据前序遍历的特点，然后复现前序遍历的过程，并用 preorderRootIndex 来记录
     * 前序遍历的结点的位置。
     */
     private int[] preorder;
     private int[] inorder;
     private int preorderRootIndex = 0;

     public TreeNode buildTree(int[] preorder, int[] inorder) {
         this.preorder = preorder;
         this.inorder = inorder;
         return buildTreeHelp(0, preorder.length - 1);
     }

     private TreeNode buildTreeHelp(int left, int right) {
         if (left > right || preorderRootIndex >= preorder.length) {
             return null;
         }
         int rootValue = preorder[preorderRootIndex];
         TreeNode root = new TreeNode(rootValue);
         // 在中序序列中找该子树的根，并以此划分左右子树
         int inorderRootIndex = findInorderRoot(left, right, rootValue);
         preorderRootIndex++;
         // 递归构建左子树
         root.left = buildTreeHelp(left, inorderRootIndex - 1);
         // 递归构建右子树
         root.right = buildTreeHelp(inorderRootIndex + 1, right);

         return root;
     }

    /**
     * 从中序序列中
     */
     private int findInorderRoot(int left, int right, int target) {
         while (left < right) {
             if (target == inorder[left]) {
                 return left;
             }
             left++;
         }

         return left;
     }

    /**
     * 写法2
      */
//    private int[] preorder;
//    private int[] inorder;
//
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        if (preorder.length == 0) {
//            return null;
//        }
//        this.preorder = preorder;
//        this.inorder = inorder;
//        TreeNode root = buildTreeHelp(0, preorder.length - 1, 0, inorder.length - 1);
//
//        return root;
//    }
//
//    private TreeNode buildTreeHelp(int preLeft, int preRight, int inLeft, int inRight) {
//        if (preLeft == preRight) {
//            return new TreeNode(preorder[preLeft]);
//        }
//        TreeNode root = new TreeNode(preorder[preLeft]);
//        int inorderRootIndex = findInorderRoot(inLeft, inRight, preorder[preLeft]);
//        if (inorderRootIndex > inLeft) {
//            root.left = buildTreeHelp(preLeft + 1, preLeft + 1 + (inorderRootIndex - 1 - inLeft), inLeft, inorderRootIndex - 1);
//        }
//        if (inorderRootIndex < inRight) {
//            root.right = buildTreeHelp(preRight - (inRight - inorderRootIndex - 1), preRight, inorderRootIndex + 1, inRight);
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
//
//        return left;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
