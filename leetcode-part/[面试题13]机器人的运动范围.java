//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 1： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
//


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 渣渣解法。利用Stream和String的API来求数位之和。时间消耗很大。
     */

    private boolean[][] isVisited;
    int result = 0;

    public int movingCount(int m, int n, int k) {
        isVisited = new boolean[m][n];
        movingHelp(0, 0, m, n, k);
        return result;
    }

    public void movingHelp(int x, int y, int m, int n, int k) {
        if (Arrays.stream((x + "" + y).split("")).mapToInt(Integer::valueOf).sum() > k) {
            return;
        }
        result++;
        isVisited[x][y] = true;
        if (x + 1 < m && !isVisited[x + 1][y]) {
            movingHelp(x + 1, y, m, n, k);
        }
        if (y + 1 < n && !isVisited[x][y + 1]) {
            movingHelp(x, y + 1, m, n, k);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
