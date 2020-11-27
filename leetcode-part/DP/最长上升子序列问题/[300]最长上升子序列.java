//给定一个无序的整数数组，找到其中最长上升子序列的长度。 
//
// 示例: 
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4 
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 
//
// 说明: 
//
// 
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 
// 你算法的时间复杂度应该为 O(n2) 。 
// 
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗? 
// Related Topics 二分查找 动态规划 
// 👍 850 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路1：dp
     * 时间复杂度：O(n^2)，空间 O(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len]; // 以nums[i]结尾的最长递增子序列的长度
        dp[0] = 1;
        int maxLength = 1;
        for (int i = 0; i < len; i++) {
            // 遍历前面的dp[]，找到序列尾比nums[i]小的所有序列中，长度最大的序列
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    /**
     * 思路2: DP。 加入二分优化
     * dp[] 数组： i 位置记录最长上升子序列长度为 i-1 的最后一个元素的值。
     * 时间复杂度：O(n log n)，空间复杂度：O(n)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] lastNum = new int[len]; // i 位置记录 maxlength=i-1 的递增子序列的最后一个元素的值。lastNum[]一定是递增的
        lastNum[0] = nums[0];
        int maxLength = 1;
        // 遍历 nums 数组，对于 nums[i]:
        // 1. 如果 nums[i] 比已记录的最长上升子序列的最后一个元素还大，则 maxLen++，并更新 maxLen 对应的位置的元素值
        // 2. 否则，在 dp 数组中二分查找第一个大于 nums[i] 的元素，并替换掉。此时 maxLen 不会更新。
        // 这个过程相当于在遍历的过程中不断用符合条件的更小的值来替换原有子序列的对应位置
        for (int i = 1; i < len; i++) {
            if (nums[i] > lastNum[maxLength - 1]) { // 当出现了比 maxLength 处更大的数，则序列增长
                maxLength++;
                lastNum[maxLength - 1] = nums[i];
            } else { // 当小于等于的时候，则在前面 maxLength-1 个位置中找到第一个大于等于 nums[i] 的数，然后替换为 nums[i]。此时 lastNum 数组长度不会增长，只是更新 lastNum 数组，以应对后面有更小元素对应更长递增序列的情况。
                int left = 0, right = maxLength - 1;
                while (left < right) {
                    int mid = left + ((right - left) >> 1);
                    if (lastNum[mid] >= nums[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                lastNum[left] = nums[i];
            }
        }

        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
