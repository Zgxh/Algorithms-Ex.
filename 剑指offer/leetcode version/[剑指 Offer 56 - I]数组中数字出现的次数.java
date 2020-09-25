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
// 2 <= nums.length <= 10000 
// 
//
// 
// 👍 227 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] singleNumbers(int[] nums) {
        // 1. 求两个不一样的数字的异或值
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        // 2. 根据这个异或值找到a和b不同的位,即异或值为1的位，该位上a和b的位值不同
        int bit = 1;
        while ((xor & bit) == 0) {
            bit <<= 1;
        }
        // 3. 按该位把所有数字分成两组，分别异或得到a和b
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & bit) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
