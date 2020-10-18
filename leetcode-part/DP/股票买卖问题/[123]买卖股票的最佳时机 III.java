//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [3,3,5,0,0,3,1,4]
//输出: 6
//解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1] 
//输出: 0 
//解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。 
// Related Topics 数组 动态规划 
// 👍 528 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // dp 法，以数组的任一点为割点，把数组分成前后两部分，分别计算最大利润，最后汇总
    // 其中0~i从左往右算最大利润，i+1~len从右往左算最大利润的相反数（因为是反着算的）
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2]; // 第1维对应0~i的最大利润，第2维对应i~len-1的最大利润的相反数
        // 从左至右更新dp的第一维
        int curMin = prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] - curMin);
            curMin = Math.min(curMin, prices[i]);
        }
        // 从右至左更新dp的第二维
        int curMax = prices[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            dp[i][1] = Math.min(dp[i + 1][1], prices[i] - curMax);
            curMax = Math.max(curMax, prices[i]);
        }
        // 遍历所有的cutPoint，求最大的利润和
        int result = 0;
        for (int i = 0; i < len; i++) {
            // 两个区间保证在i的地方重叠，即0~i算一次i，i~len-1再算一次i，是为了算上只卖一次股票的情况
            int profit = dp[i][0] - dp[i][1];
            result = Math.max(result, profit);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
