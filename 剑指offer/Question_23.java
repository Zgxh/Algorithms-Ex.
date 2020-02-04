/**
 * 剑指offer第23题：二叉搜索树的后序遍历序列
 *
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * @author Yu Yang
 * @create 2020-02-02 19:23
 */
public class Question_23 {

    /**
     * 思路：递归判断子树是否满足BST的特点。根据后序遍历的特点，对于一个子树，根是最后遍历到的。
     * 根据根找到左右子树的分界点，然后递归判断左右子树是否满足BST的特点。
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return isBST(sequence, 0, sequence.length - 1);
    }

    /**
     * 判断子树是否为BST
     * @param sequence
     * @param start
     * @param root
     * @return
     */
    public boolean isBST(int[] sequence, int start, int root) {
        if (start >= root) {
            return true;
        }
        int rightStart = root; // 初始化为root，防止全是左子树的情况出现
        // 找到左右子树的分界点
        for (int i = start; i < root; i++) {
            if (sequence[i] > sequence[root]) {
                rightStart = i;
                break;
            }
        }
        // 根据BST的特点，右子树结点都大于root，判断是否满足这个条件
        for (int i = rightStart + 1; i < root; i++) {
            if (sequence[i] < sequence[root]) {
                return false;
            }
        }
        // 递归判断小子树是否满足BST的条件
        return isBST(sequence, start, rightStart - 1) &&
                isBST(sequence, rightStart, root - 1);
    }
}
