import java.util.ArrayList;

/**
 * 剑指offer第24题：二叉树中和为某一值的路径
 *
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 *
 * @author Yu Yang
 * @create 2020-02-04 16:32
 */
public class Question_24 {

    /**
     * 思路：有点先序遍历的意思。这里是采用递归实现的，代码如下，逻辑不复杂。
     */
    private ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    private ArrayList<Integer> curPath = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        find(root, target, 0);
        return result;
    }

    public void find(TreeNode node, int target, int curLength) {
        if (node == null || curLength + node.val > target) { // 空指针返回，应对的是单孩子的情况
            return;
        }
        if (node.left == null && node.right == null) { // 是叶子，且等于target即找到一条路径
            if (curLength + node.val == target) {
                curPath.add(node.val);
                result.add(new ArrayList(curPath));
                curPath.remove(curPath.size() - 1);
            }
            return;
        }
        curPath.add(node.val);
        find(node.left, target, curLength + node.val);
        find(node.right, target, curLength + node.val);
        curPath.remove(curPath.size() - 1);
    }

//    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
//        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//        if (root == null || target <= 0) {
//            return result;
//        }
//        LinkedList<TreeNode> stack = new LinkedList<>(); // stack辅助进行先序遍历
//        ArrayList<Integer> currentPath = new ArrayList<>(); // 记录当前从root开始走过的路径
//        TreeNode temp = root;
//        int pathLength = 0;
//        boolean isRight = true;
//        while (temp != null || !stack.isEmpty()) {
//            if (temp != null && pathLength + temp.val <= target) {
//                stack.push(temp);
//                currentPath.add(temp.val);
//                pathLength += temp.val;
//                // 当temp为叶子时，判断是否满足条件
//                if (temp.left == null && temp.right == null && pathLength == target) {
//                    result.add(new ArrayList<>(currentPath));
//                }
//                temp = temp.left;
//                isRight = false;
//            } else {
//                if (isRight) { // temp是右孩子
//                    pathLength -= stack.pop().val;
//                    currentPath.remove(currentPath.size() - 1);
//                }
//                temp = stack.peek().right;
//                isRight = true;
//            }
//        }
//        return result;
//    }

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
