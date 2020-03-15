/*
 * @lc app=leetcode.cn id=264 lang=java
 *
 * [264] 丑数 II
 */
class Solution {
    public int nthUglyNumber(int n) {
        /**
         * 采用动态规划+三指针。三个指针分别代表下一个与2,3,5相乘的数组元素，保证每个
         * 数组元素只被乘一遍。三个指针有快有慢，从 1 开始不断相乘，每次将最小的放在
         * 数组最后面，从而保证结果的顺序正确。
         */
        int i2 = 0; 
        int i3 = 0;
        int i5 = 0; 
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(dp[i2] * 2, dp[i3] * 3), dp[i5] * 5);
            // 这里必须用3个if，而不是if - elseif - else，因为会有重复的数。比如2*3和3*2
            if (min == dp[i2] * 2) {
                i2++;
            }
            if (min == dp[i3] * 3) {
                i3++;
            }
            if (min == dp[i5] * 5) {
                i5++;
            }
            dp[i] = min;
        }
        return dp[n - 1];
    }
}

