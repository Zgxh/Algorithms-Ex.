//给定一个未排序的整数数组，找到最长递增子序列的个数。 
//
// 示例 1: 
//
// 
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
// 
//
// 示例 2: 
//
// 
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
// 
//
// 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。 
// Related Topics 动态规划 
// 👍 237 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // dp 问题。当前状态与之前的 n-1 个状态都有关。
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[][] dp = new int[len][2]; // 递增子序列以 nums[i] 为结尾时的最大长度，和构成该长度的子序列的所有可能情况数
        dp[0][0] = 1;
        dp[0][1] = 1;
        int maxLen = 1;
        int count = 1;
        for (int i = 1; i < len; i++) {
            int tempMax = 0, maxCount = 1;
            for (int j = 0; j < i; j++) {
                // 若遇到更大的长度，则更新为该长度，并初始化maxCount为对应的情况数
                // 若遇到了与maxLen相同的长度，则出现最大长度的情况数增加
                if (nums[j] < nums[i]) {
                    if (tempMax == dp[j][0]) {
                        maxCount += dp[j][1];
                    } else if (tempMax < dp[j][0]) {
                        tempMax = dp[j][0];
                        maxCount = dp[j][1];
                    }
                }
            }
            // 更新 dp 数组
            dp[i][0] = tempMax + 1;
            dp[i][1] = maxCount;
            // 更新最大长度、对应的子序列个数
            if (maxLen == dp[i][0]) {
                count += dp[i][1];
            } else if (maxLen < dp[i][0]) {
                maxLen = dp[i][0];
                count = dp[i][1];
            }
        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
