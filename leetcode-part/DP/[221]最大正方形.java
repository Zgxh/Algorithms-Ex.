//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。 
//
// 示例: 
//
// 输入: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4 
// Related Topics 动态规划 
// 👍 579 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP
    // dp[][]存放以i，j为右下角顶点的最大正方形的边长
    // 传递函数：dp[i][j] = min(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]) + 1
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int iLen = matrix.length, jLen = matrix[0].length;
        int[][] dp = new int[iLen][jLen]; // 以i，j位置为右下角的最大正方形的边长
        int maxSide = 0;
        // 初始化dp数组
        for (int i = 0; i < iLen; i++) {
            dp[i][0] = matrix[i][0] - '0';
            if (dp[i][0] == 1) {
                maxSide = 1;
            }
        }
        for (int j = 0; j < jLen; j++) {
            dp[0][j] = matrix[0][j] - '0';
            if (dp[0][j] == 1) {
                maxSide = 1;
            }
        }
        // 更新dp数组
        for (int i = 1; i < iLen; i++) {
            for (int j = 1; j < jLen; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxSide * maxSide;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
