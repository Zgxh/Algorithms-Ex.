//实现一个基本的计算器来计算一个简单的字符串表达式的值。 
//
// 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格 。 整数除法仅保留整数部分。 
//
// 示例 1: 
//
// 输入: "3+2*2"
//输出: 7
// 
//
// 示例 2: 
//
// 输入: " 3/2 "
//输出: 1 
//
// 示例 3: 
//
// 输入: " 3+5 / 2 "
//输出: 5
// 
//
// 说明： 
//
// 
// 你可以假设所给定的表达式都是有效的。 
// 请不要使用内置的库函数 eval。 
// 
// Related Topics 字符串


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：把加减法的项直接入栈，把乘除法的算完了再入栈。栈中只放数字，不放运算符。
     * @param s
     * @return
     */
    public int calculate(String s) {

        LinkedList<Integer> stack = new LinkedList<>();
        char lastOp = '+';
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                continue;
            }
            if (Character.isDigit(arr[i])) {
                int curNum = arr[i] - '0';
                while (++i < arr.length && Character.isDigit(arr[i])) {
                    curNum = curNum * 10 + (arr[i] - '0');
                }
                i--;
                if (lastOp == '+') {
                    stack.push(curNum);
                } else if (lastOp == '-') { // 减法加负号
                    stack.push(-curNum);
                } else {
                    stack.push(product(lastOp, stack.pop(), curNum));
                }
            } else {
                lastOp = arr[i];
            }
        }

        int result = 0;
        for (int num : stack) {
            result += num;
        }
        
        return result;
    }

    /**
     * 求乘除法
     * @param op
     * @param a
     * @param b
     * @return
     */
    private int product(char op, int a, int b) {
        if (op == '*') {
            return a * b;
        } else {
            return a / b;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
