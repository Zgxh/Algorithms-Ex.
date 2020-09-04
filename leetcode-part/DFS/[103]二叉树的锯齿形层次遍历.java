//给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层次遍历如下： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 栈 树 广度优先搜索 
// 👍 259 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    // // BFS 写法。 下面的代码使用的是对queue进行反序，其实也可以按照正常的层序遍历的顺序来，
    // // 然后通过reversed值判断当前应该进行的遍历方向，在把curLayer插入result的时候才进行反序。
    // public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    //     List<List<Integer>> result = new ArrayList();
    //     if (root == null) {
    //         return result;
    //     }
    //     LinkedList<TreeNode> queue = new LinkedList(); // 这里要用Queue的List实现类，否则用不了Collections.reverse()方法
    //     queue.offer(root);
    //     int layerNum = 1, count = 0;
    //     List<Integer> curLayer = new ArrayList();
    //     boolean reversed = false; // true表示当前行是从右往左遍历的
    //     while (!queue.isEmpty()) {
    //         TreeNode temp = queue.poll();
    //         curLayer.add(temp.val);
    //         count++;
    //         if (reversed) {
    //             if (temp.right != null) {
    //                 queue.offer(temp.right);
    //             }
    //             if (temp.left != null) {
    //                 queue.offer(temp.left);
    //             }
    //         } else {
    //             if (temp.left != null) {
    //                 queue.offer(temp.left);
    //             }
    //             if (temp.right != null) {
    //                 queue.offer(temp.right);
    //             }
    //         }
    //         if (count == layerNum) {
    //             result.add(curLayer);
    //             curLayer = new ArrayList();
    //             count = 0;
    //             layerNum = queue.size();
    //             Collections.reverse(queue);
    //             reversed = !reversed; // 每过一层转换一个方向
    //         }
    //     }

    //     return result;
    // }

    // DFS写法
    private List<List<Integer>> result;

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        result = new ArrayList();
        if (root == null) {
            return result;
        }
        dfs(root, 0);

        return result;
    }

    private void dfs(TreeNode root, int level) {
        if (level >= result.size()) { // dfs总是从上往下的，所以当前层肯定是紧接着result中的最底层
            LinkedList<Integer> curLayer = new LinkedList();
            curLayer.add(root.val);
            result.add(curLayer);
        } else {
            LinkedList<Integer> curLayer = (LinkedList) result.get(level);
            if (level % 2 == 0) {
                curLayer.addLast(root.val);
            } else {
                curLayer.addFirst(root.val);
            }
        }
        if (root.left != null) {
            dfs(root.left, level + 1);
        }
        if (root.right != null) {
            dfs(root.right, level + 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
