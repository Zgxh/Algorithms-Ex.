//
//
// 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿
//过地下城并通过对抗恶魔来拯救公主。 
//
// 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。 
//
// 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么
//包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。 
//
// 为了尽快到达公主，骑士决定每次只向右或向下移动一步。 
//
// 
//
// 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。 
//
// 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。 
//
// 
// 
// -2 (K) 
// -3 
// 3 
// 
// 
// -5 
// -10 
// 1 
// 
// 
// 10 
// 30 
// -5 (P) 
// 
// 
//
//
// 
//
// 说明: 
//
// 
// 
// 骑士的健康点数没有上限。 
// 
// 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。 
// Related Topics 二分查找 动态规划


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 此题的重点在于不光要让到达终点后的生命值大于0，在路上也必须大于0
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        // dp[i][j]: 要想从[i][j]到达终点，在走到[i][j]位置前，至少需要多少初始生命(不包含[i][j]提供的)
        int[][] dp = new int[m + 1][n + 1]; // dp[0~m-1][0~n-1] 对应 dungeon[0~m-1][0~n-1]
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 多加的一行一列是为了保证到达终点后，剩余生命值至少为 1。因为 dp 维护的是到达该点之前的最小生命值。
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minLife = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]; // 至少多少生命，就是最小的初始生命，所以 min
                dp[i][j] = Math.max(1, minLife); // 到达每个点之前的初始生命必须大于0
            }
        }

        return dp[0][0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
