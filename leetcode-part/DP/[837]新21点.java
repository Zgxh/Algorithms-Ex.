//爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下： 
//
// 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次
//抽取都是独立的，其结果具有相同的概率。 
//
// 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？ 
//
// 
//
// 示例 1： 
//
// 输入：N = 10, K = 1, W = 10
//输出：1.00000
//说明：爱丽丝得到一张卡，然后停止。 
//
// 示例 2： 
//
// 输入：N = 6, K = 1, W = 10
//输出：0.60000
//说明：爱丽丝得到一张卡，然后停止。
//在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。 
//
// 示例 3： 
//
// 输入：N = 21, K = 17, W = 10
//输出：0.73278 
//
// 
//
// 提示： 
//
// 
// 0 <= K <= N <= 10000 
// 1 <= W <= 10000 
// 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。 
// 此问题的判断限制时间已经减少。 
// 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：dp
     * 最终获胜的概率与上一轮得分有关。
     * @param N
     * @param K
     * @param W
     * @return
     */
    public double new21Game(int N, int K, int W) {
        if (K == 0 || K - 1 + W <= N) {
            return 1.0d;
        }
        double[] dp = new double[K + W]; // 默认初始值是0.0d；存放上一次值为i时，获胜的概率
        for (int i = K; i <= N && i <= K - 1 + W; i++) {
            dp[i] = 1.0d;
        }
        dp[K - 1] = 1.0 * (N - (K - 1)) / W;
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + 1 + W] - dp[i + 1]) / W; // dp转移方程：dp[i]与dp[i+1]正好错开一项
        }

        return dp[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
