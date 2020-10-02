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

    /**
     * 1. 遍历数组中的每个元素，如果数字在 1~n 之间，则被交换到指定的位置：
     * 如 i 被交换到 index=i-1 的位置，交换后 i 之前的位置可能需要递归交换，
     * 直到数字变成大于n的值，或者如果被交换的二者是相等的则直接终止。
     * 2. 最后遍历数组，找到位置与值不对应的第一个位置。
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
