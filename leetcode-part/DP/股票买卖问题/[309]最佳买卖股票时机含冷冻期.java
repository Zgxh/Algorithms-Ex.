//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
// Related Topics 动态规划 
// 👍 581 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 动态规划。买入代表负收益，卖出代表正收益。
    // dp[i][3]: 第一维代表天数，第二维代表状态：
    // 0 代表当前持有股票；
    // 1 代表已卖出，是当前天卖出的，处于冷冻期
    // 2 代表已卖出，是之前卖出的，不处于冷冻期
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[len][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }

        return Math.max(dp[len - 1][1], dp[len - 1][2]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
