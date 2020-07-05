//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：利用栈，存放括号的index。
     * 遇到'('，则把index压入栈；遇到')'，则把栈顶弹出；每次遇到')'，都计算一下当前合法序列的总长度；
     * 如果栈空了，则把当前的')'的index入栈。 -> 因为要计算序列长度。
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(-1);
        char[] sArr = s.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (sArr[i] == '(') {
                stack.addFirst(i);
            } else {
                stack.removeFirst();
                if (stack.isEmpty()) {
                    stack.addFirst(i);
                }
                maxLen = Math.max(maxLen, i - stack.getFirst());
            }
        }

        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
