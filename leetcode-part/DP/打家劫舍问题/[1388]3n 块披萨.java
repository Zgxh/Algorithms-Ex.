//给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨： 
//
// 
// 你挑选 任意 一块披萨。 
// Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。 
// Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。 
// 重复上述过程直到没有披萨剩下。 
// 
//
// 每一块披萨的大小按顺时针方向由循环数组 slices 表示。 
//
// 请你返回你可以获得的披萨大小总和的最大值。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：slices = [1,2,3,4,5,6]
//输出：10
//解释：选择大小为 4 的披萨，Alice 和 Bob 分别挑选大小为 3 和 5 的披萨。然后你选择大小为 6 的披萨，Alice 和 Bob 分别挑选大小
//为 2 和 1 的披萨。你获得的披萨总大小为 4 + 6 = 10 。
// 
//
// 示例 2： 
//
// 
//
// 输入：slices = [8,9,8,6,1,1]
//输出：16
//解释：两轮都选大小为 8 的披萨。如果你选择大小为 9 的披萨，你的朋友们就会选择大小为 8 的披萨，这种情况下你的总和不是最大的。
// 
//
// 示例 3： 
//
// 输入：slices = [4,1,2,5,8,3,1,9,7]
//输出：21
// 
//
// 示例 4： 
//
// 输入：slices = [3,1,2]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= slices.length <= 500 
// slices.length % 3 == 0 
// 1 <= slices[i] <= 1000 
// 
// Related Topics 动态规划 
// 👍 59 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 此问题限制了需要获得的披萨数，相当于打家劫舍2的升级版
    // DP 问题。用到了2维DP数组，同时记录当前考虑过的数组长度，与当前已获得的披萨数。
    // 环形数组问题，开头元素和结尾元素只能使用一个，分为2中情况考虑：0~len-2, 1~len-1.
    public int maxSizeSlices(int[] slices) {
        int len = slices.length;
        if (len == 0) {
            return 0;
        }

        return Math.max(sizeHelp(slices, 0, len - 2), sizeHelp(slices, 1, len - 1));
    }

    private int sizeHelp(int[] slices, int left, int right) {
        if (left > right) {
            return 0;
        }
        int len = right - left + 1;
        int choose = (len + 1) / 3; // 要获得的披萨数
        int[][] dp = new int[len + 1][choose + 1]; // dp[i][j]: 在前i块披萨中拿到了j块时的最大披萨大小
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= choose; j++) {
                // 当前块不拿，则直接借用上一块的状态；
                // 当前块拿，则使用隔一块的状态 + 当前块的大小
                dp[i][j] = Math.max(dp[i - 1][j], (i - 2 >= 0 ? dp[i - 2][j - 1] : 0) + slices[left + i - 1]);
            }
        }

        return dp[len][choose];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
