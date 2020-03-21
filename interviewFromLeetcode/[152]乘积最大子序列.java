//给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。 
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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：因为给的都是整数，除了0外直接连乘。
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {

        int max = Integer.MIN_VALUE;
        int currentMax = 1, currentMin = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) { // 交换正负
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }
            currentMax = Math.max(currentMax * nums[i], nums[i]);
            currentMin = Math.min(currentMin * nums[i], nums[i]);
            max = Math.max(currentMax, max);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
