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
// 👍 136 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP 问题。
    // DP[][] 表示以[0,0]为左上角，[i,j]为右下角的矩形的和
    // 求解子矩阵的最大和时，添加O(n)的优化；
    // 然后因为加了不超过k的约束，所以可能需要继续O(iLen^2) 遍历
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int iLen = 0, jLen = 0;
        if ((iLen = matrix.length) == 0 || (jLen = matrix[0].length) == 0) {
            return 0;
        }
        long[][] dp = new long[iLen + 1][jLen + 1]; // 表示以[0,0]为左上角，[i,j]为右下角的矩形的和
        for (int i = 1; i <= iLen; i++) {
            for (int j = 1; j <= jLen; j++) {
                dp[i][j] = matrix[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        long max = Long.MIN_VALUE;
        // O(iLen^2 * jLen^2)
        for (int j1 = 0; j1 < jLen; j1++) {
            for (int j2 = j1 + 1; j2 <= jLen; j2++) {
                // 先通过 O(iLen) 的方式求解 i1~i2 的最大和，此时不加 <=k 的约束
                // 若这样求出来的最大和还小于k，那么就不需要通过 O(iLen^2) 的方式来进行遍历，因为结果肯定是一样的
                int i1 = 0;
                long tempMax = Long.MIN_VALUE;
                for (int i2 = 1; i2 <= iLen; i2++) {
                    long tempSum = dp[i2][j2] - dp[i2][j1] - dp[i1][j2] + dp[i1][j1];
                    if (tempSum > tempMax) {
                        tempMax = tempSum;
                    }
                    if (tempSum < 0) {
                        i1 = i2;
                    }
                }
                if (tempMax <= k) { // 如果不加约束求出来还小于k，则不需要往下进行
                    max = Math.max(max, tempMax);
                    continue;
                }
                // 如果不加约束求出来大于k，则需要进行 O(iLen^2) 遍历
                for (i1 = 0; i1 < iLen; i1++) {
                    for (int i2 = i1 + 1; i2 <= iLen; i2++) {
                        long tempSum = dp[i2][j2] - dp[i2][j1] - dp[i1][j2] + dp[i1][j1];
                        if (tempSum <= k && tempSum > max) {
                            max = tempSum;
                        }
                    }
                }
            }
        }

        return (int) max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
