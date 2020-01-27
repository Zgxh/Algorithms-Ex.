import java.util.LinkedList;

/**
 * 剑指offer第5题：用两个栈实现队列
 *
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * @author Yu Yang
 * @create 2020-01-23 19:36
 */
public class Question_5 {

    /**
     * 思路：压入时，直接push入stack1;
     * 弹出时，若stack2不空，直接弹出stack2栈顶；
     *        若stack2空，则先将stack1全部元素逐个压入stack2，再弹出stack2栈顶。
     */
    LinkedList<Integer> stack1 = new LinkedList<>();
    LinkedList<Integer> stack2 = new LinkedList<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
