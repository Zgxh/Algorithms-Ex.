//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划 
// 👍 1021 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * DP前缀和问题 -> 前缀积问题
     * 利用dp数组同时维护最大积与最小积，因为有可能是负数
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[][] dp = new int[len][2]; // 0 维度代表最大，1 维度代表最小
        dp[0][0] = dp[0][1] = nums[0];
        int result = nums[0];
        for (int i = 1; i < len; i++) {
            // 为避免负数存在
            dp[i][0] = Math.max(nums[i], Math.max(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
            dp[i][1] = Math.min(nums[i], Math.min(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
            result = Math.max(result, dp[i][0]);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
