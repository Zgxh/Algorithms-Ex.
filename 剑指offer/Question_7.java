/**
 * 剑指offer第7题：斐波那契数列
 *
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 *
 * @author Yu Yang
 * @create 2020-01-27 18:29
 */
public class Question_7 {

    /**
     * 使用数组储存中间结果，避免递归运算中的重复计算；下面的代码：时间：O(n)，空间O(n).
     * 可以进一步优化，使用2个单位的空间来储存前两个值，空间复杂度优化为O(1)
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
}
