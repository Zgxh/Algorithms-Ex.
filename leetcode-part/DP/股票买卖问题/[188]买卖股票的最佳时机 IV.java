//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [2,4,1], k = 2
//输出: 2
//解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
// 
//
// 示例 2: 
//
// 输入: [3,2,6,5,0,3], k = 2
//输出: 7
//解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。
// 
// Related Topics 动态规划 
// 👍 314 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 动态规划
    // dp[n][k][2]: 第一维代表天数，第二维代表已完成k次交易，第三维代表当前是买入还是卖出:0卖出，1买入
    // dp值代表当前状态下的最大利润
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        // 当允许交易次数足够大时,当做没有交易次数限制，即【买卖股票2】
        int result = 0;
        if (k >= len / 2) { // 一次买入，一次卖出，至少占用两天
            for (int i = 1; i < len; i++) {
                if (prices[i] > prices[i - 1]) {
                    result += prices[i] - prices[i - 1];
                }
            }
            return result;
        }
        // 优化三维dp为二维dp
        int[][] dp = new int[k + 1][2];
        // dp 初始化，第一天
        for (int i = 0; i <= k; i++) {
            dp[i][0] = 0;
            dp[i][1] = -prices[0];
        }
        // dp 更新
        for (int i = 1; i < len; i++) {
            for (int j = k; j >= 1; j--) {
                // 处理第j次买入：要么就是之前就已经买入了；要么就是之前完成了j-1次卖出，当前是第j次买入
                dp[j - 1][1] = Math.max(dp[j - 1][1], dp[j - 1][0] - prices[i]);
                // 处理第j次卖出：要么就是之前已经卖出了j次，当前不操作；要么就已经完成了j-1次且之前已买入，这次要卖出
                dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] + prices[i]);
            }
        }

        return dp[k][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
