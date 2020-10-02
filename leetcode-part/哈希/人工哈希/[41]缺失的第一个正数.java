//给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,0]
//输出: 3
// 
//
// 示例 2: 
//
// 输入: [3,4,-1,1]
//输出: 2
// 
//
// 示例 3: 
//
// 输入: [7,8,9,11,12]
//输出: 1
// 
//
// 
//
// 提示： 
//
// 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。 
// Related Topics 数组 
// 👍 810 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 手动哈希：把1~n之间的值看成哈希index
    // 利用原始nums数组进行存储
    // 1.遍历nums数组，把<=0的元素设置为大于n的值
    // 2.遍历nums数组，把1~n之间的数对应的index上的值标记为负值，但绝对值不变
    // 3.遍历nums数组，找到第一个大于0的index
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            if (nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }
        for (int i = 0; i < len; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= len) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < len; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return len + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
