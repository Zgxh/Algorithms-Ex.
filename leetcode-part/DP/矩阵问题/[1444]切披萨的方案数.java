//给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。你需要切披萨 k-1
// 次，得到 k 块披萨并送给别人。 
//
// 切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平
//地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。 
//
// 请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：pizza = ["A..","AAA","..."], k = 3
//输出：3 
//解释：上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
// 
//
// 示例 2： 
//
// 输入：pizza = ["A..","AA.","..."], k = 3
//输出：1
// 
//
// 示例 3： 
//
// 输入：pizza = ["A..","A..","..."], k = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= rows, cols <= 50 
// rows == pizza.length 
// cols == pizza[i].length 
// 1 <= k <= 10 
// pizza 只包含字符 'A' 和 '.' 。 
// 
// Related Topics 动态规划 
// 👍 36 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * DP + DFS
     * 通过dp数组首先计算出苹果数目，然后可以直接利用该数组计算某几行、某几列之间
     * 有无苹果。
     * 然后再通过 dfs + 缓存 的方式遍历所有的切割方案。
     */

    private Map<Integer, Integer> cache;
    private char[][] pizza;
    private int[][] dp;
    private int iLen, jLen;
    private long base = (long)1e9 + 7;

    public int ways(String[] pizza, int k) {
        this.iLen = pizza.length;
        this.jLen = pizza[0].length();
        this.pizza = new char[iLen][jLen];
        for (int i = 0; i < iLen; i++) {
            this.pizza[i] = pizza[i].toCharArray();
        }
        this.cache = new HashMap();
        // dp[i][j]: pizza[i~iLen-1][j~jLen-1] 之间的苹果数
        // 通过 dp[i][j]-dp[m][n] 来判断某几行、某几列之间有无苹果
        this.dp = new int[iLen + 1][jLen + 1];
        for (int i = iLen - 1; i >= 0; i--) {
            for (int j = jLen - 1; j >= 0; j--) {
                dp[i][j] = (pizza[i].charAt(j) == 'A' ? 1 : 0) + dp[i + 1][j] + dp[i][j + 1] - dp[i + 1][j + 1];
            }
        }

        return (int) dfs(0, 0, k);
    }

    private long dfs(int i, int j, int k) {
        if (k == 1) {
            return dp[i][j] > 0 ? 1 : 0;
        }
        if (dp[i][j] < k || k == 0) { // 如果要判断的区域总苹果数还不足 k 个，那没必要判断
            return 0;
        }
        Integer key = 2500 * i + 50 * j + k;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        long result = 0;
        // 从上往下考虑删除行的情况
        for (int x = i + 1; x < iLen; x++) {
            if (dp[i][j] - dp[x][j] == 0) { // 如果被删除的区域不含苹果
                continue;
            }
            // 如果包含苹果
            long temp = dfs(x, j, k - 1);
            if (temp == 0) { // 如果剩余区域不足以分为k-1份，则没必要往下进行了
                break;
            }
            result = (result + temp) % base;
        }
        // 从左往右考虑删除列的情况
        for (int y = j + 1; y < jLen; y++) {
            if (dp[i][j] - dp[i][y] == 0) { // 如果被删除的区域不含苹果
                continue;
            }
            // 如果包含苹果
            long temp = dfs(i, y, k - 1);
            if (temp == 0) { // 如果剩余区域不足以分为k-1份，则没必要往下进行了
                break;
            }
            result = (result + temp) % base;
        }
        cache.put(key, (int)result);

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
