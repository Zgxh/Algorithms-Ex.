//给定一个平衡括号字符串 S，按下述规则计算该字符串的分数： 
//
// 
// () 得 1 分。 
// AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。 
// (A) 得 2 * A 分，其中 A 是平衡括号字符串。 
// 
//
// 
//
// 示例 1： 
//
// 输入： "()"
//输出： 1
// 
//
// 示例 2： 
//
// 输入： "(())"
//输出： 2
// 
//
// 示例 3： 
//
// 输入： "()()"
//输出： 2
// 
//
// 示例 4： 
//
// 输入： "(()(()))"
//输出： 6
// 
//
// 
//
// 提示： 
//
// 
// S 是平衡括号字符串，且只含有 ( 和 ) 。 
// 2 <= S.length <= 50 
// 
// Related Topics 栈 字符串


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：栈。
     * 遇到左括号就放进去，右括号弹出。
     * 注意'()'对应的是1，而不是0。
     * @param S
     * @return
     */
    public int scoreOfParentheses(String S) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(0);

        for (char c: S.toCharArray()) {
            if (c == '(')
                stack.addFirst(0);
            else {
                int v = stack.removeFirst();
                int w = stack.removeFirst();
                stack.addFirst(w + Math.max(2 * v, 1)); // important
            }
        }

        return stack.removeFirst();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
