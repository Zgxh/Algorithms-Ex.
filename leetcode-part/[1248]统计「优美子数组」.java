//给你一个整数数组 nums 和一个整数 k。 
//
// 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。 
//
// 请返回这个数组中「优美子数组」的数目。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [1,1,2,1,1], k = 3
//输出：2
//解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
// 
//
// 示例 2： 
//
// 输入：nums = [2,4,6], k = 1
//输出：0
//解释：数列中不包含任何奇数，所以不存在优美子数组。
// 
//
// 示例 3： 
//
// 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
//输出：16
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 50000 
// 1 <= nums[i] <= 10^5 
// 1 <= k <= nums.length 
// 
// Related Topics 双指针


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 数学方法。oddIndex记录每个奇数的下标，每次取k个连续的奇数，然后算包含这k个奇数的序列个数。
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {

        int[] oddIndex = new int[nums.length + 2]; // 多给2个位置存放开始和结束
        int index = 1;
        int result = 0;

        for (int i = 0; i < nums.length; i++) { // 记录奇数的下标
            if (nums[i] % 2 == 1) {
                oddIndex[index++] = i;
            }
        }
        oddIndex[0] = -1;
        oddIndex[index] = nums.length;

        for (int i = 1; i + k - 1 < index; i++) { // 前后两端区间大小的乘积就是所有包含该k个奇数的序列个数
            result += (oddIndex[i] - oddIndex[i - 1]) *
                    (oddIndex[i + k] - oddIndex[i + k - 1]);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
