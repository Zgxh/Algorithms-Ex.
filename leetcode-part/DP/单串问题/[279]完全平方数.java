//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。 
//
// 示例 1: 
//
// 输入: n = 12
//输出: 3 
//解释: 12 = 4 + 4 + 4. 
//
// 示例 2: 
//
// 输入: n = 13
//输出: 2
//解释: 13 = 4 + 9. 
// Related Topics 广度优先搜索 数学 动态规划 
// 👍 683 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP。
    public int numSquares(int n) {
        List<Integer> list = new ArrayList();
        int base = 1;
        while (base * base <= n) {
            list.add(base * base);
            base++;
        }
        int[] nums = list.stream().mapToInt(Integer::intValue).toArray();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        // 从小到大遍历i，直到n，对每个i，根据dp[0~i-1]，遍历所有可能的组合，找最小的
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < nums.length && nums[j] <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - nums[j]] + 1);
            }
        }

        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
