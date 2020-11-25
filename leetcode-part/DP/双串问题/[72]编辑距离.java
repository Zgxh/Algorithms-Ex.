//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 1265 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP 双串问题。
    // 把情况分为增、删、改三种。其中改的情况分为当前对应位置相同与不同，分别处理。
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1]; // dp[i][j]：串1的前i个字符，与串2的前j个字符，满足题意时的最小修改次数
        for (int i = 0; i <= len1; i++) {
            Arrays.fill(dp[i], 1000);
        }
        // DP 初始化，其中一个字符串为空时，直接把另一个非空的字符全部删除即为最小情况
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 改：把i，j位置修改为相同的
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) { // 本来就相同，不用改
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                } else { // 本来不同，则改任何一个
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                }
                // 删和增的传递关系是相同的：
                // 删：删除第i个或第j个
                // 增：在第i个后增，或在第j个后增
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
            }
        }

        return dp[len1][len2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
