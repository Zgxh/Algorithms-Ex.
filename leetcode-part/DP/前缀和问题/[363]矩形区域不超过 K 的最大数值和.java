//给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。 
//
// 示例: 
//
// 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
//输出: 2 
//解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 说明： 
//
// 
// 矩阵内的矩形区域面积必须大于 0。 
// 如果行数远大于列数，你将如何解答呢？ 
// 
// Related Topics 队列 二分查找 动态规划 
// 👍 168 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 前缀和。
     * 当行数远大于列数的时候，尝试对i采用O(n)的方式进行优化
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int iLen = 0, jLen = 0;
        if ((iLen = matrix.length) == 0 || (jLen = matrix[0].length) == 0) {
            return 0;
        }
        long[][] dp = new long[iLen + 1][jLen + 1];
        for (int i = 1; i <= iLen; i++) {
            for (int j = 1; j <= jLen; j++) {
                dp[i][j] = matrix[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        long result = Long.MIN_VALUE;
        // 当行数远大于列数的时候，尝试对i采用O(n)的方式进行优化
        for (int j1 = 0; j1 < jLen; j1++) {
            for (int j2 = j1 + 1; j2 <= jLen; j2++) {
                //
                // 首先尝试O(n)的方法直接求解最大和，如果最大和<=k，则满足条件
                // 否则，再尝试通过O(n^2)的方式去求解
                int i1 = 0;
                long tempMax = Long.MIN_VALUE;
                for (int i2 = 1; i2 <= iLen; i2++) {
                    long temp = dp[i2][j2] - dp[i1][j2] - dp[i2][j1] + dp[i1][j1];
                    if (temp > tempMax) {
                        tempMax = temp;
                    }
                    if (temp < 0) {
                        i1 = i2;
                    }
                }
                // 如果直接就满足<=k的要求
                if (tempMax <= k) {
                    result = Math.max(result, tempMax);
                    continue;
                }
                // 不满足要求，则采用O(n^2)的方式遍历
                for (i1 = 0; i1 < iLen; i1++) {
                    for (int i2 = i1 + 1; i2 <= iLen; i2++) {
                        long temp = dp[i2][j2] - dp[i1][j2] - dp[i2][j1] + dp[i1][j1];
                        if (temp <= k && temp > result) {
                            result = temp;
                        }
                    }
                }
            }
        }

        return (int) result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
