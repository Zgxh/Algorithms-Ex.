//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。 
//
// 
//
// 参考以下这颗二叉搜索树： 
//
//      5
//    / \
//   2   6
//  / \
// 1   3 
//
// 示例 1： 
//
// 输入: [1,6,3,2,5]
//输出: false 
//
// 示例 2： 
//
// 输入: [1,3,2,6,5]
//输出: true 
//
// 
//
// 提示： 
//
// 
// 数组长度 <= 1000 
// 
// 👍 115 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 递归解法。
     * 分别判断 根、左子树、右子树。
     */
    private int[] postorder;

    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) {
            return true;
        }
        this.postorder = postorder;
        int len = postorder.length;

        return isPostorderHelp(0, len - 1);
    }

    private boolean isPostorderHelp(int left, int right) {
        if (left >= right) {
            return true;
        }
        int leftBegin = left;
        // 划分左子树，左子树所有节点都小于根节点的值
        while (postorder[left] < postorder[right]) {
            left++;
        }
        int rightBegin = left;
        // 划分右子树，右子树所有节点都大于根节点的值
        while (postorder[left] > postorder[right]) {
            left++;
        }

        // 1.保证左右子树划分完之后,index正好到达根节点，即左右子树值的大小满足二叉搜索树的条件
        // 2.递归检验左子树
        // 3.递归检验右子树
        return left == right && isPostorderHelp(leftBegin, rightBegin - 1)
                && isPostorderHelp(rightBegin, right - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
