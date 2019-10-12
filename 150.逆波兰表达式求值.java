import java.util.Stack;

/*
 * @lc app=leetcode.cn id=150 lang=java
 *
 * [150] 逆波兰表达式求值
 */

// @lc code=start
class Solution {

    /**
     * 实现逆波兰表达式求值。
     * 做法：遇到数字就放入堆栈，遇到运算符就弹出栈顶两个元素，运算符左边是次顶，
     * 右边是栈顶。
     * 辅助使用正则表达式匹配，字符串与整数之间的解析。
     * @param tokens 
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            //判断是否为数字，使用正则表达式
            if (token.matches("-?\\d+")) {
                stack.push(token);
            } else {
                //把字符串解析为多位整数
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                //分别匹配四种运算符
                if (token.equals("+")) {
                    res = num2 + num1;
                } else if (token.equals("-")) {
                    res = num2 - num1;
                } else if (token.equals("*")) {
                    res = num2 * num1;
                } else {
                    res = num2 / num1;
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
// @lc code=end

