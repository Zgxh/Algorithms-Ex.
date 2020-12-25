//给定一个整数数组 A，返回 A 中最长等差子序列的长度。 
//
// 回想一下，A 的子序列是列表 A[i_1], A[i_2], ..., A[i_k] 其中 0 <= i_1 < i_2 < ... < i_k <= A
//.length - 1。并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 B 是等差的。 
//
// 
//
// 示例 1： 
//
// 输入：[3,6,9,12]
//输出：4
//解释： 
//整个数组是公差为 3 的等差数列。
// 
//
// 示例 2： 
//
// 输入：[9,4,7,2,10]
//输出：3
//解释：
//最长的等差子序列是 [4,7,10]。
// 
//
// 示例 3： 
//
// 输入：[20,1,15,3,10,5,8]
//输出：4
//解释：
//最长的等差子序列是 [20,15,10,5]。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length <= 2000 
// 0 <= A[i] <= 10000 
// 
// Related Topics 动态规划 
// 👍 93 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP。时间复杂度 O(n^2)
    // 对每个元素，遍历他前面的所有可能的前一项，以此确定等差间距，然后利用dp数组递推。
    public int longestArithSeqLength(int[] A) {
        int len = A.length;
        if (len < 3) {
            return len;
        }
        int[][] dp = new int[len][20001];
        int maxLen = 0;
        // 对i位置的元素，遍历它所有可能的前一项A[j]
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int interval = A[i] - A[j] + 10000; // 等差间距，加10000是为了把负数变成正数，防止数组越界问题
                if (dp[j][interval] > 0) {
                    dp[i][interval] = Math.max(dp[i][interval], dp[j][interval] + 1);
                } else {
                    dp[i][interval] = 2;
                }
                maxLen = Math.max(maxLen, dp[i][interval]);
            }
        }

        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
