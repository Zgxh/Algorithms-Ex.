/*
 * @lc app=leetcode.cn id=312 lang=java
 *
 * [312] 戳气球
 */
class Solution {

    /**
     * 解题思路：在i到j区间，用k表示[i+1, j-1]中最后戳破的那个气球，
     * 用dp[i][j]表示戳破[i+1, j-1]所有气球的最大收益
     * dp[i][j] = max{dp[i][k] + dp[k][j] + newNums[i]*newNums[k]*newNums[j]}
     * 对于所有的k in [i+1, j-1]
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        //把原数组两头各加个 1
        int[] newNums = new int[nums.length + 2];
        newNums[0] = 1;
        newNums[newNums.length - 1] = 1;
        for (int i = 1; i < newNums.length - 1; i++) {
            newNums[i] = nums[i - 1];
        }
        //dp[i][j]表示戳破[i+1, j-1]号气球的最大收益
        int[][] dp = new int[newNums.length][newNums.length];
        //len用来控制子序列长度，子序列长度从3开始，一直到整个序列长度
        for (int len = 2; len < newNums.length; len++) {
            for (int i = 0; i < newNums.length - len; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + 
                                        newNums[i] * newNums[k] * newNums[j]);
                }
            }
        }
        return dp[0][newNums.length - 1];
    }
}

