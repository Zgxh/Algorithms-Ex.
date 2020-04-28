//一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [4,1,4,6]
//输出：[1,6] 或 [6,1]
// 
//
// 示例 2： 
//
// 输入：nums = [1,2,10,4,1,4,3,3]
//输出：[2,10] 或 [10,2] 
//
// 
//
// 限制： 
//
// 
// 2 <= nums <= 10000 
// 
//
// 
//


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路类似于数组中一个出现一次的数字类似。使用异或。
     * 先异或一遍得到所有数字异或后的结果，然后从右至左找到第一个为1的位。
     * 目标的两个数字在该位必定不同，以此来区分这两个数字。
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        int judge = 0;
        for (int num : nums) {
            judge ^= num;
        }

        // 找到第一个为1的位
        int mask = 1;
        while ((judge & mask) == 0) {
            mask <<= 1;
        }

        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((mask & num) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }

        return new int[]{num1, num2};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
