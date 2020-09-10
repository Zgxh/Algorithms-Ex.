//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配
//是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。 
// 
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
// 
// Related Topics 动态规划 
// 👍 98 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 递归判断。
     * 总共存在三种字符：ch . *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // 当主串为空时，模式串必须为空或者全为 [ch*] 的组合
        if (s.length() == 0) {
            if (p.length() % 2 != 0) {
                return false;
            }
            int i = 1;
            while (i < p.length()) {
                if (p.charAt(i) != '*') {
                    return false;
                }
                i += 2;
            }
            return true;
        }
        // 主串不空但模式串为空时，直接false
        if (p.length() == 0) {
            return false;
        }
        char curCharInS = s.charAt(0), curCharInP = p.charAt(0), pNext = ' '; // pNext来判断p的下一位是不是 *
        if (p.length() > 1) {
            pNext = p.charAt(1);
        }
        // 分为有 * 和无 * 的情况
        if (pNext == '*') {
            // 如果当前位能匹配上，可以选择 * 匹配或者不匹配
            if (curCharInS == curCharInP || curCharInP == '.') {
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            }
            // 当前位不能匹配，则 * 只能选择跳过，一次也不匹配
            return isMatch(s, p.substring(2));
        } else {
            if (curCharInS == curCharInP || curCharInP == '.') {
                return isMatch(s.substring(1), p.substring(1));
            }
            // 当前位不能匹配，直接返回 false
            return false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
