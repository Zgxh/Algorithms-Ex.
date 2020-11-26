//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 724 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 动态规划：每个位置上的数字总和与它上边和左边的元素有关
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int iLen = grid.length, jLen = grid[0].length;
        // 把二维 dp 优化为1维dp，因为该行的i位置只与上一行的i位置，还有当前行的i-1位置有关
        int[] dp = new int[jLen + 1];
        // 初始化dp数组
        dp[0] = grid[0][0];
        for (int j = 1; j < jLen; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        // 更新dp数组
        for (int i = 1; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (j == 0) {
                    dp[0] += grid[i][0];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }

        return dp[jLen - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
