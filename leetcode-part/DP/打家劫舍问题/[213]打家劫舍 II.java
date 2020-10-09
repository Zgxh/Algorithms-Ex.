//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋
//装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。 
//
// 示例 1: 
//
// 输入: [2,3,2]
//输出: 3
//解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,1]
//输出: 4
//解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
// Related Topics 动态规划 
// 👍 389 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 动态规划。 因为0位置和len-1位置不能同时被打劫，所以分三种情况进行考虑：
    // 第一种：0~len-2中打劫
    // 第二种：1~len-1中打劫
    // 第三种：1~len-2中打劫，这种情况包含在了前两种里
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        }

        return Math.max(robHelp(nums, 0, len - 2), robHelp(nums, 1, len - 1));
    }

    private int robHelp(int[] nums, int start, int end) {
        int len = end - start + 1;
        // int[] dp = new int[len + 2]; // DP数组可以被两个int值代替
        int preTwo = 0, preOne = 0;
        while (start <= end) {
            int temp = preOne;
            preOne = Math.max(preOne, preTwo + nums[start]);
            preTwo = temp;
            start++;
        }

        return preOne;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
