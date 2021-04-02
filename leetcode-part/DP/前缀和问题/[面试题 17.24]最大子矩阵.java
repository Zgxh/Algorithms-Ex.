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
// 👍 68 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 前缀和问题。
     * 对于第二维，采用O(n)的方式进行优化。
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        int iLen = 0, jLen = 0;
        if ((iLen = matrix.length) == 0 || (jLen = matrix[0].length) == 0) {
            return new int[0];
        }
        // 求前缀和
        int[][] dp = new int[iLen + 1][jLen + 1];
        for (int i = 1; i <= iLen; i++) {
            for (int j = 1; j <= jLen; j++) {
                dp[i][j] = matrix[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        int[] result = {};
        // 固定i1和i2，对j1和j2采用寻找最大子数组和的方法来优化O(n^2)到O(n)
        for (int i1 = 0; i1 < iLen; i1++) {
            for (int i2 = i1 + 1; i2 <= iLen; i2++) {
                // 采用寻找最大子数组和的方式来对j1，j2进行遍历
                int j1 = 0;
                for (int j2 = 1; j2 <= jLen; j2++) {
                    int temp = dp[i2][j2] - dp[i2][j1] - dp[i1][j2] + dp[i1][j1];
                    if (temp > max) {
                        max = temp;
                        result = new int[]{i1, j1, i2 - 1, j2 - 1};
                    }
                    // 前面的值小于0，则对总和无贡献，需要舍弃
                    if (temp < 0) {
                        j1 = j2;
                    }
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
