//有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。 
//
// 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 lef
//t 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 说明: 
//
// 
// 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。 
// 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100 
// 
//
// 示例: 
//
// 输入: [3,1,5,8]
//输出: 167 
//解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
// 
// Related Topics 分治算法 动态规划 
// 👍 562 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 转移方程：戳破左边的 + 戳破右边的 + 当前的(nums[left] * nums[i] * nums[right])
    public int maxCoins(int[] nums) {
        int numsLen = nums.length;
        // -1 位置和 numLen 位置可以看做是 1，构造新数组
        int newNumsLen = numsLen + 2;
        int[] newNums = new int[numsLen + 2];
        newNums[0] = newNums[newNumsLen - 1] = 1;
        for (int i = 1; i < newNumsLen - 1; i++) {
            newNums[i] = nums[i - 1];
        }
        int[][] dp = new int[newNumsLen][newNumsLen]; // dp[i][j]表示戳破i~j中间的所有气球，不包括i和j
        for (int hop = 2; hop < newNumsLen; hop++) {
            for (int i = 0; i < newNumsLen - hop; i++) { // 沿着对角线方向一层一层更新 dp 数组
                int j = i + hop;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i] * newNums[j] * newNums[k]);
                }
            }
        }

        return dp[0][newNumsLen - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
