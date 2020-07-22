//给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。 
//
// 示例 1: 
//
// 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//输出: false 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：动态规划。 （双指针是错误的）
     *
     * 用dp数组来记录s1的前i个字符和s2的前j个字符，是否能交叉组成s3的前i+j个字符。
     * 分为两种情况：s1的第i个字符与s3的第i+j个字符相同；s2的第j个字符与s3的第i+j个字符相同。
     * 所以dp[i][j]的值应该是用两种情况取或运算得来的。
     * (而双指针则只考虑了一种情况，所以是错误的。)
     *
     * 优化：把二维数组优化成一维dp数组，因为计算当前的dp只需要上一行的dp值和当前行的前一个index的dp值。
     * 从而使空间复杂度降低。
     */

    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) { // 长度不一致，必然是false
            return false;
        }
        boolean[] dp = new boolean[len2 + 1]; // 初始默认值是false
        dp[0] = true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                int p = i + j - 1; // 第i+j个字符
                if (i > 0) {
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(p)); // 注意dp数组的index与字符串的index差了1
                }
                if (j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return dp[len2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
