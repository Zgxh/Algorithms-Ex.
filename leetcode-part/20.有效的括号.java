import java.util.Stack;

/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 */
class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int length = s.length();
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.add(s.charAt(i));
            }
            else {
                if (stack.empty()) {
                    return false;
                }
                if (s.charAt(i) == ')' && stack.peek() == '(') {
                    stack.pop();
                }
                else if (s.charAt(i) == ']' && stack.peek() == '[') {
                    stack.pop();
                }
                else if (s.charAt(i) == '}' && stack.peek() == '{') {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }
        return stack.empty() ? true : false;
    }
}

