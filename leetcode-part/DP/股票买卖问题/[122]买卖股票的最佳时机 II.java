//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
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
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10 ^ 4 
// 0 <= prices[i] <= 10 ^ 4 
// 
// Related Topics 贪心算法 数组 
// 👍 886 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // // 峰谷法：遇到极大值时，把股票卖出。因为在山峰的下个点买入会更便宜，只赚不赔。
    // public int maxProfit(int[] prices) {
    //     int len = prices.length;
    //     if (len <= 1) {
    //         return 0;
    //     }
    //     int result = 0;
    //     int curMin = prices[0];
    //     for (int i = 1; i < len - 1; i++) {
    //         // 遇到合适的山峰，则把股票卖出，同时更新当前交易的最小买入值
    //         if (prices[i] > curMin
    //                 && prices[i] > prices[i + 1]) {
    //             result += prices[i] - curMin;
    //             curMin = prices[i]; // 更新最小值为当前值
    //         }
    //         curMin = Math.min(curMin, prices[i]);
    //     }
    //     // 最后一个点不能进行山峰的判断，需要额外处理
    //     if (prices[len - 1] > curMin) {
    //         result += prices[len - 1] - curMin;
    //     }

    //     return result;
    // }

    // 优化的山峰法：只要是上坡，就在低点买入，高点卖出
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int result = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1]) {
                result += prices[i] - prices[i - 1];
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
