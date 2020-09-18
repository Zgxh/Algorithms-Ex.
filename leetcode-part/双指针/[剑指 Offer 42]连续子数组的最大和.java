//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。 
//
// 要求时间复杂度为O(n)。 
//
// 
//
// 示例1: 
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^5 
// -100 <= arr[i] <= 100 
// 
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/ 
//
// 
// Related Topics 分治算法 动态规划 
// 👍 126 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 双指针问题
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = 0;
        int max = nums[0], sum = nums[0];
        int len = nums.length;
        while (left < len - 1 && right < len - 1) {
            if (sum <= 0) {
                left = right + 1;
                right = left;
                sum = nums[left];
            } else {
                right++;
                sum += nums[right];
            }
            max = Math.max(max, sum);
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
