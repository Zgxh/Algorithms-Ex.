//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 数学 
// 👍 72 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // // 方法1：利用TreeSet，但要使用long来避免数值溢出
    // public int nthUglyNumber(int n) {
    //     long[] factors = {2, 3, 5};
    //     TreeSet<Long> sortedSet = new TreeSet();
    //     sortedSet.add(1L);
    //     for (int i = 0; i < n - 1; i++) {
    //         long setMin = sortedSet.pollFirst();
    //         for (long factor : factors) {
    //             sortedSet.add(factor * setMin);
    //         }
    //     }

    //     return sortedSet.first().intValue();
    // }

    // 方法2：指针法
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int pointer2 = 0, pointer3 = 0, pointer5 = 0;
        for (int i = 1; i < n; i++) {
            int num2 = dp[pointer2] * 2, num3 = dp[pointer3] * 3, num5 = dp[pointer5] * 5;
            dp[i] = Math.min(num2, Math.min(num3, num5));
            // 因为三个值可能重复等于目标值，所以不能用else if
            if (dp[i] == num2) {
                pointer2++;
            }
            if (dp[i] == num3) {
                pointer3++;
            }
            if (dp[i] == num5) {
                pointer5++;
            }
        }

        return dp[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
