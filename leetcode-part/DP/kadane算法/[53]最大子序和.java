//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 2658 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // // dp 问题。当前状态只依赖前一个状态。
    // // dp[i] = nums[i] + Math.max(dp[i - 1], 0)

    // public int maxSubArray(int[] nums) {
    //     int len = nums.length;
    //     int[] dp = new int[len]; // 以 nums[i] 为结尾的子数组的最大和
    //     dp[0] = nums[0];
    //     int max = dp[0];
    //     for (int i = 1; i < len; i++) {
    //         dp[i] = nums[i] + Math.max(dp[i - 1], 0);
    //         max = Math.max(dp[i], max);
    //     }

    //     return max;
    // }

    // 可以优化为 O(1) 空间复杂度的
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            result = Math.max(result, sum);
            if (sum < 0) {
                sum = 0;
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
