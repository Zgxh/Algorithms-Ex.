//给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limi
//t 。 
//
// 如果不存在满足条件的子数组，则返回 0 。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [8,2,4,7], limit = 4
//输出：2 
//解释：所有子数组如下：
//[8] 最大绝对差 |8-8| = 0 <= 4.
//[8,2] 最大绝对差 |8-2| = 6 > 4. 
//[8,2,4] 最大绝对差 |8-2| = 6 > 4.
//[8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
//[2] 最大绝对差 |2-2| = 0 <= 4.
//[2,4] 最大绝对差 |2-4| = 2 <= 4.
//[2,4,7] 最大绝对差 |2-7| = 5 > 4.
//[4] 最大绝对差 |4-4| = 0 <= 4.
//[4,7] 最大绝对差 |4-7| = 3 <= 4.
//[7] 最大绝对差 |7-7| = 0 <= 4. 
//因此，满足题意的最长子数组的长度为 2 。
// 
//
// 示例 2： 
//
// 输入：nums = [10,1,2,4,7,2], limit = 5
//输出：4 
//解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
// 
//
// 示例 3： 
//
// 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10^5 
// 1 <= nums[i] <= 10^9 
// 0 <= limit <= 10^9 
// 
// Related Topics 数组 Sliding Window 
// 👍 79 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 双指针 + 单调队列：同时使用单调递增序列和单调递减序列来维护最大值与最小值
    public int longestSubarray(int[] nums, int limit) {
        int left = 0;
        int len = nums.length;
        // 单调队列，存 index
        Deque<Integer> max = new LinkedList();
        Deque<Integer> min = new LinkedList();
        int maxLen = 0;
        for (int right = 0; right < len; right++) {
            // 1. 加入 right
            while (!max.isEmpty() && nums[right] >= nums[max.getLast()]) {
                max.removeLast();
            }
            max.addLast(right);
            while (!min.isEmpty() && nums[right] <= nums[min.getLast()]) {
                min.removeLast();
            }
            min.addLast(right);
            // 2. 确定 left
            while (!max.isEmpty() && !min.isEmpty()
                    && nums[max.getFirst()] - nums[min.getFirst()] > limit) {
                left++;
                if (left > max.getFirst()) {
                    max.removeFirst();
                }
                if (left > min.getFirst()) {
                    min.removeFirst();
                }
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
