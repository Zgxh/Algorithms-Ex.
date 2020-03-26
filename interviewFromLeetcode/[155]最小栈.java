//设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) -- 将元素 x 推入栈中。 
// pop() -- 删除栈顶的元素。 
// top() -- 获取栈顶元素。 
// getMin() -- 检索栈中的最小元素。 
// 
//
// 示例: 
//
// MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
// Related Topics 栈 设计


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

    /**
     * 思路：使用辅助栈存放当前的最小元素。其中数据栈与辅助栈同步。
     */

    private Stack<Integer> data; // data存放数据
    private Stack<Integer> helper; // helper存放当前数据容量下的最小值

    /** initialize your data structure here. */
    public MinStack() {
        data = new Stack<Integer>();
        helper = new Stack<Integer>();
    }

    public void push(int x) {
        data.add(x);
        if (helper.isEmpty() || helper.peek() >= x) {
            helper.add(x);
        } else {
            helper.add(helper.peek());
        }
    }

    public void pop() {
        if (!data.isEmpty()) {
            data.pop();
            helper.pop();
        }
    }

    public int top() {
        if (!data.isEmpty()) {
            return data.peek();
        }
        throw new RuntimeException("栈中无元素，非法操作！");
    }

    public int getMin() {
        if (!helper.isEmpty()) {
            return helper.peek();
        }
        throw new RuntimeException("栈中无元素，非法操作！");
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)
