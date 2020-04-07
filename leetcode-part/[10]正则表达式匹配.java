//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// '.' 匹配任意单个字符
//'*' 匹配零个或多个前面的那一个元素
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 
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
// Related Topics 字符串 动态规划 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 1. 回溯法。如果没有'*',则直接单个字符匹配。s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'
     * 如果有'*',则可以匹配0次或多次。如果0次，直接p的前两个字符去掉；如果多次，则先判断第一个字符是否匹配，然后递归判断。
     * @param s 待匹配串
     * @param p 模式串
     * @return
     */
    public boolean isMatch1(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        boolean headMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'); // 第一个字符是否匹配
        if (p.length() >= 2 && p.charAt(1) == '*') { // 碰到'*'，要么匹配0次，要么匹配多次
            return isMatch1(s, p.substring(2)) || (headMatch && isMatch1(s.substring(1), p));
        } else if (headMatch) {
            return isMatch1(s.substring(1), p.substring(1));
        } else {
            return false;
        }
    }

    /**
     * 2. 动态规划。dp[i][j]就是s的前i个元素是否可以被p的前j个元素所匹配。
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true; // 第一列和dp[0][1]默认为false

        for (int j = 2; j < p.length() + 1; j++) { // s为空的情况
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (p.charAt(j - 1) == '*') { // 匹配0次，则舍弃p的最近2个字符；匹配多次，则判断是否匹配（前提是前面的已经匹配成功）
                    boolean curMatched = s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.';
                    dp[i][j] = dp[i][j - 2] || (dp[i - 1][j] && curMatched);
                } else {
                    boolean curMatched = s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.';
                    dp[i][j] = dp[i - 1][j - 1] && curMatched;
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
