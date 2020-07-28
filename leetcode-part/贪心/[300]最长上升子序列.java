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
     * 思路2： dp + 二分
     * 使用dp数组，dp[i]存放maxLength为i+1时对应的递增子序列的序列尾；
     * 更新原则采取贪心，总是找更小的值来代替原序列的最大值；
     * 在贪心替换的过程中，由于lastNum数组是单调的，所以可以通过二分查找来加速。
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] lastNum = new int[len]; // 记录maxlength为i+1的递增子序列，其最后一个元素的值。lastNum[]一定是递增的
        lastNum[0] = nums[0];
        int maxLength = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > lastNum[maxLength - 1]) { // 当出现了比maxLength处更大的数，则序列增长
                maxLength++;
                lastNum[maxLength - 1] = nums[i];
            } else { // 当小于等于的时候，则在前面i-1个位置中找到第一个大于等于nums[i]的数，然后贪心替换掉
                int left = 0, right = maxLength - 1;
                while (left < right) { // 二分结束后，left==right为lastNum[]中大于等于nums[i]的第一个元素
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    }
}
//leetcode submit region end(Prohibit modification and deletion)
