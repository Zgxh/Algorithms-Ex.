import java.util.Stack;
/*
 * @lc app=leetcode.cn id=155 lang=java
 *
 * [155] 最小栈
 */

// @lc code=start
class MinStack {

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
// @lc code=end

