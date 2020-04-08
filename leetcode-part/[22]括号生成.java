//给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。 
//
// 例如，给出 n = 3，生成结果为： 
//
// [
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
// 
// Related Topics 字符串 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：回溯法。
     * 如果左括号小于n个，可以加左括号；如果右括号数目小于左括号数目，则可以加右括号。
     */

    private int max;
    private List<String> result;

    public List<String> generateParenthesis(int n) {
        max = n;
        result = new ArrayList<>();
        generateHelp(new StringBuilder(), 0, 0);
        return result;
    }

    /**
     * 回溯
     *
     * @param sb 当前字符串
     * @param open sb中的左括号数目
     * @param close sb中的右括号数目
     */
    private void generateHelp(StringBuilder sb, int open, int close) {
        if (sb.length() == 2 * max) {
            result.add(sb.toString());
        }
        if (open < max) { // 左括号小于n个
            sb.append('(');
            generateHelp(sb, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) { // 右括号数目小于左括号数目
            sb.append(')');
            generateHelp(sb, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
