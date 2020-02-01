import java.util.LinkedList;

/**
 * 剑指offer第20题：包含min函数的栈
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 *
 * @author Yu Yang
 * @create 2020-02-01 16:42
 */
public class Question_20 {

    /**
     * 使用两个栈：其中一个用来记录min值的变化，并随主栈中元素的进出而进出。
     */

    LinkedList<Integer> stack = new LinkedList<>();
    LinkedList<Integer> minElement = new LinkedList<>();

    public void push(int node) {
        stack.push(node);
        if (minElement.isEmpty()) {
            minElement.push(node);
        } else {
            minElement.push(minElement.peek() <= node ? minElement.peek() : node);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            minElement.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minElement.peek();
    }
}
