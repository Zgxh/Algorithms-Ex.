////给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
////
//// 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
////
//// 返回获得利润的最大值。
////
//// 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
////
//// 示例 1:
////
//// 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
////输出: 8
////解释: 能够达到的最大利润:
////在此处买入 prices[0] = 1
////在此处卖出 prices[3] = 8
////在此处买入 prices[4] = 4
////在此处卖出 prices[5] = 9
////总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
////
//// 注意:
////
////
//// 0 < prices.length <= 50000.
//// 0 < prices[i] < 50000.
//// 0 <= fee < 50000.
////
//// Related Topics 贪心算法 数组 动态规划
//// 👍 212 👎 0
//
//
////leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 动态规划。
    // dp[len][2]，第一维代表天数，第二维代表当前是否持有股票
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        if (len <= 1) {
            return 0;
        }
        int[][] dp = new int[len][2]; // 0代表不持有股票，1代表持有股票
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[len - 1][0];
    }

    // // 空间优化
    // public int maxProfit(int[] prices, int fee) {
    //     // result 代表当前不持有股票，最大利润
    //     // purchased 代表当前持有股票，最大利润
    //     int result = 0, purchased = -prices[0];
    //     int len = prices.length;
    //     for (int i = 1; i < len; i++) {
    //         result = Math.max(result, purchased + prices[i] - fee);
    //         purchased = Math.max(purchased, result - prices[i]);
    //     }

    //     return result;
    // }
}
////leetcode submit region end(Prohibit modification and deletion)
