import java.util.LinkedList;

/**
 * 剑指offer第21题：栈的压入、弹出序列
 *
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该
 * 压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 *
 * @author Yu Yang
 * @create 2020-02-01 20:40
 */
public class Question_21 {

    /**
     * 思路：利用两个数组，模拟数组元素的出栈入栈。不断入栈pushA[]中的元素，一旦栈顶元素跟popA[]一致，
     * 就出栈。最后判断栈空否。
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null) {
            return false;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0, j = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
