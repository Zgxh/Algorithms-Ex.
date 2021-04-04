//给定一个正整数数组 nums。 
//
// 找出该数组内乘积小于 k 的连续的子数组的个数。 
//
// 示例 1: 
//
// 
//输入: nums = [10,5,2,6], k = 100
//输出: 8
//解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
//需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
// 
//
// 说明: 
//
// 
// 0 < nums.length <= 50000 
// 0 < nums[i] < 1000 
// 0 <= k < 10^6 
// 
// Related Topics 数组 双指针 
// 👍 229 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 前缀积问题，为了避免乘法溢出，把乘法转化为对数的加法
    // 使用 binary search 来加快查找的效率
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int len = nums.length;
        double[] product = new double[len + 1];
        int result = 0;
        double logK = Math.log(k);
        for (int i = 1; i <= len; i++) {
            // 乘法转化为对数的加法
            product[i] = product[i - 1] + Math.log(nums[i - 1]);
            int left = 0, right = i - 1;
            // 二分查找第一个小于 logK 的位置
            while (left < right) {
                int mid = left + (right - left) / 2;
                // 除法转化为对数的减法
                double arrProduct = product[i] - product[mid];
                if (arrProduct >= logK - 1e-9) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (product[i] - product[left] < logK - 1e-9) {
                result += i - left;
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
