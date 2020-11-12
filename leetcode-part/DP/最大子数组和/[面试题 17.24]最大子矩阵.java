//给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。 
//
// 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满
//足条件的子矩阵，返回任意一个均可。 
//
// 注意：本题相对书上原题稍作改动 
//
// 示例： 
//
// 输入：
//[
//   [-1,0],
//   [0,-1]
//]
//输出：[0,1,0,1]
//解释：输入中标粗的元素即为输出所表示的矩阵 
//
// 
//
// 说明： 
//
// 
// 1 <= matrix.length, matrix[0].length <= 200 
// 
// Related Topics 动态规划 
// 👍 36 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP问题。
    // DP[][] 存放从[0,0]到[i,j]的矩形的和。
    // 计算子矩阵的最大和时，固定一对边界（行或列），对另一种边界优化为O(n)
    public int[] getMaxMatrix(int[][] matrix) {
        int iLen = matrix.length, jLen = matrix[0].length;
        long[][] dp = new long[iLen + 1][jLen + 1]; // initialize to 0
        for (int i = 1; i <= iLen; i++) {
            for (int j = 1; j <= jLen; j++) {
                dp[i][j] = matrix[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        long max = Long.MIN_VALUE;
        int[] result = {0, 0, 0, 0};
        // 固定 i1 和 i2，然后变化 j1 和 j2，找最大
        for (int i2 = 1; i2 <= iLen; i2++) {
            for (int i1 = 0; i1 < i2; i1++) {
                int j1 = 0;
                for (int j2 = 1; j2 <= jLen; j2++) {
                    long cur = dp[i2][j2] - dp[i1][j2] - dp[i2][j1] + dp[i1][j1];
                    if (cur > max) {
                        max = cur;
                        result = new int[]{i1, j1, i2 - 1, j2 - 1};
                    }
                    if (cur < 0) {
                        j1 = j2;
                    }
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
