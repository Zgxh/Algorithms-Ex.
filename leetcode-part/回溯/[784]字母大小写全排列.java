//给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。 
//
// 
//示例:
//输入: S = "a1b2"
//输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
//
//输入: S = "3z4"
//输出: ["3z4", "3Z4"]
//
//输入: S = "12345"
//输出: ["12345"]
// 
//
// 注意： 
//
// 
// S 的长度不超过12。 
// S 仅由数字和字母组成。 
// 
// Related Topics 位运算 回溯算法 
// 👍 190 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：回溯。
     * 深度优先，然后分别递归加入大小写字母。
     */

    private char[] chars;
    private int len = 0;
    private List<String> result = new ArrayList<>();

    public List<String> letterCasePermutation(String S) {
        chars = S.toCharArray();
        len = S.length();
        permutation(new StringBuilder(), 0);
        return result;
    }

    private void permutation(StringBuilder sb, int begin) {
        while (begin < len && Character.isDigit(chars[begin])) {
            sb.append(chars[begin]);
            begin++;
        }
        if (begin == len) {
            result.add(sb.toString());
            return;
        }
        permutation(sb.append((chars[begin] + "").toLowerCase()), begin + 1);
        sb.delete(begin, sb.length()); // 从该递归子序列的第一个字母重新开始
        permutation(sb.append((chars[begin] + "").toUpperCase()), begin + 1);
        sb.delete(begin, sb.length()); // 递归返回前取消对sb的改动
    }
}
//leetcode submit region end(Prohibit modification and deletion)
