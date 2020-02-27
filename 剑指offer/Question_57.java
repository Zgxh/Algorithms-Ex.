/**
 * 剑指offer第57题：二叉树的下一个结点
 *
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * @author Yu Yang
 * @create 2020-02-27 9:18
 */
public class Question_57 {

    /**
     * 思路：按照中序遍历的特点，
     * （1）先判断有没有右孩子：如果有，下一个遍历的就是右孩子的左孩子到底；
     * 如果没有，向上找爹，
     * （2）如果该结点是他爹的左孩子，下一个遍历的就是他爹；
     * 如果是他爹的右孩子，
     * （3）一直往上找，直到找到结点是他爹的左孩子，返回他爹；
     * 如果找到一半指针空了，则遍历完了，返回null
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        if (pNode.right != null) { // 有右孩子
            TreeLinkNode pRight = pNode.right;
            while (pRight.left != null) {
                pRight = pRight.left;
            }
            return pRight;
        } else { // 没有右孩子，得找爹
            TreeLinkNode pNext = pNode;
            while (pNext.next != null && pNext.next.right == pNext) {
                pNext = pNext.next;
            }
            if (pNext.next != null && pNext.next.left == pNext) {
                return pNext.next;
            }
            return null;
        }
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
}
