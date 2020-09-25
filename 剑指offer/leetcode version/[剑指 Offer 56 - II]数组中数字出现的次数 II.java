//在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [3,4,3,3]
//输出：4
// 
//
// 示例 2： 
//
// 输入：nums = [9,1,7,9,7,9,7]
//输出：1 
//
// 
//
// 限制： 
//
// 
// 1 <= nums.length <= 10000 
// 1 <= nums[i] < 2^31 
// 
//
// 
// 👍 79 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 每个数字都出现了三次，则对二进制位进行统计，对3取余。
     * 一种最简单的方法是遍历所有数字的32位，统计每个位置的出现频次。
     * 更有技巧性的方法：
     * 二进制存不下3个状态，所以用两位二进制位来存放每个位置的状态，这两位分别用two 和 one来表示。
     * ones 和 twos 表示同时存放32位的状态。
     */
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }

        return ones;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
