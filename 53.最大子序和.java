/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 */

// @lc code=start
class Solution {

    /**
     * 动态规划问题：设置一个sum表示当前序列的和，若sum<0，则对下次负贡献应该舍弃；
     * 若sum>0，正贡献应该保留。设置result记录当前获得的最大序列和，每次取sum与
     * result的较大者。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum >= 0) {
                sum += num;
            } else {
                sum = num;
            }
            result = Math.max(result, sum);
        }
        return result;
    }
}
// @lc code=end

