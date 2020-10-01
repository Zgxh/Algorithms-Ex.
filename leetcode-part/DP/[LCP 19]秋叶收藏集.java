//小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 `leaves`， 字符串 `leaves` 仅包含小写字符 `r` 和 `
//y`， 其中字符 `r` 表示一片红叶，字符 `y` 表示一片黄叶。
//出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替
//换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
//
//**示例 1：**
//>输入：`leaves = "rrryyyrryyyrr"`
//>
//>输出：`2`
//>
//>解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
//
//**示例 2：**
//>输入：`leaves = "ryr"`
//>
//>输出：`0`
//>
//>解释：已符合要求，不需要额外操作
//
//**提示：**
//- `3 <= leaves.length <= 10^5`
//- `leaves` 中只包含字符 `'r'` 和字符 `'y'` 👍 122 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 对每个叶子维护三种状态：0,1,2分别代表前面的r，中间的y，后面的r
     * 因为对于叶子序列，0,1,2都至少有一个，所以0号位置只能对应状态0，
     * 1号位置可以为状态0和1,从2号位置往后可以有0,1,2三种状态。
     * 递推关系：
     * 按照0,1,2三种状态分别求，0的话前面必须都是0；
     * 1的话前面可以是0和1；
     * 2的话前面必须是1或2。
     */
    public int minimumOperations(String leaves) {
        int len = leaves.length();
        int[][] dp = new int[len][3];
        dp[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            int turnToRed = leaves.charAt(i) == 'y' ? 1 : 0;
            int turnToYellow = leaves.charAt(i) == 'r' ? 1 : 0;
            dp[i][0] = dp[i - 1][0] + turnToRed;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + turnToYellow;
            if (i >= 2) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + turnToRed;
            }
        }

        return dp[len - 1][2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
