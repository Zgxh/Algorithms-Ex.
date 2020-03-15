/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 */

// @lc code=start
class Solution {
    
    /**
     * 简单的动态规划问题：分析其思路，发现当n>3时，可以选择n=n-2时一下爬2阶
     * 或者是n=n-1时爬一阶，所以opt(n)=opt(n-1)+opt(n-2).
     * @param n
     * @return
     */
    public int climbStairs(int n) {
         if (n == 0) {
             return 0;
         } 
         if (n == 1) {
             return 1;
         }
         if (n == 2) {
             return 2;
         }
         int[] result = new int[n + 1];
         result[1] = 1;
         result[2] = 2;
         for (int i = 3; i <= n; i++) {
             result[i] = result[i - 1] + result[i - 2];
         }
         return result[n];
    }
}
// @lc code=end

