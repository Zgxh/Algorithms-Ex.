//实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。 
//
// 调用 next() 将返回二叉搜索树中的下一个最小的数。 
//
// 
//
// 示例： 
//
// 
//
// BSTIterator iterator = new BSTIterator(root);
//iterator.next();    // 返回 3
//iterator.next();    // 返回 7
//iterator.hasNext(); // 返回 true
//iterator.next();    // 返回 9
//iterator.hasNext(); // 返回 true
//iterator.next();    // 返回 15
//iterator.hasNext(); // 返回 true
//iterator.next();    // 返回 20
//iterator.hasNext(); // 返回 false 
//
// 
//
// 提示： 
//
// 
// next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。 
// 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。 
// 
// Related Topics 栈 树 设计 
// 👍 301 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BSTIterator {

    // // 方法1：提前拿到中序遍历的列表，然后对列表进行随机查找

    // List<Integer> iterator;
    // int index;

    // public BSTIterator(TreeNode root) {
    //     this.iterator = new ArrayList();
    //     inorder(root);
    // }

    // public int next() {
    //     int next = iterator.get(index);
    //     index++;

    //     return next;
    // }

    // public boolean hasNext() {
    //     return index < iterator.size();
    // }

    // private void inorder(TreeNode root) {
    //     TreeNode pointer = root;
    //     Deque<TreeNode> stack = new ArrayDeque();
    //     while (pointer != null || !stack.isEmpty()) {
    //         if (pointer != null) {
    //             stack.push(pointer);
    //             pointer = pointer.left;
    //         } else {
    //             TreeNode node = stack.pop();
    //             this.iterator.add(node.val);
    //             pointer = node.right;
    //         }
    //     }
    // }

    // 方法2：受控递归，在调用 next() 的过程中进行递归，递归到下一个被中序遍历的结点为止
    private Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new ArrayDeque();
        stageInorder(root);
    }

    private void stageInorder(TreeNode root) {
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode next = this.stack.pop();
        if (next.right != null) {
            stageInorder(next.right);
        }

        return next.val;
    }

    public boolean hasNext() {
        return !this.stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
//leetcode submit region end(Prohibit modification and deletion)
