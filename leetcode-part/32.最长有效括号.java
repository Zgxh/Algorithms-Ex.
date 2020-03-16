import java.util.Stack;
/*
 * @lc app=leetcode.cn id=32 lang=java
 *
 * [32] 最长有效括号
 */
class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len == 0) 
            return 0;
        //char[] charArray = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') 
                stack.push(i);
            else {
                stack.pop();
                if (stack.empty()) 
                    stack.push(i);
                else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}
