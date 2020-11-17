//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 
//
// 返回符合要求的最少分割次数。 
//
// 示例: 
//
// 输入: "aab"
//输出: 1
//解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
// 
// Related Topics 动态规划 
// 👍 221 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP。利用DP数组，先求解任意划分是否为回文串
    // 再利用 result[] 数组，实质也是一个 DP 数组，通过之前的状态来求解当前状态的最少划分次数
    public int minCut(String s) {
        int len = s.length();
        // 求解 dp 数组, O(n ^ 2)
        boolean[][] dp = new boolean[len][len];
        for (int interval = 0; interval < len; interval++) {
            for (int i = 0; i < len - interval; i++) {
                int j = i + interval;
                if (s.charAt(i) == s.charAt(j) && (interval <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        // 求解所有的情况，O(n ^ 2)
        int[] result = new int[len]; // result[i] 即 0~len-1 之间的回文子串最少需要的划分次数
        Arrays.fill(result, Integer.MAX_VALUE); // 初始化为一个大值
        for (int j = 0; j < len; j++) {
            if (dp[0][j]) {
                result[j] = 0;
                continue;
            }
            for (int i = 0; i <= j; i++) {
                if (dp[i][j]) {
                    result[j] = Math.min(result[j], result[i - 1] + 1); // 利用之前的状态来求解当前状态
                }
            }
        }

        return result[len - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
