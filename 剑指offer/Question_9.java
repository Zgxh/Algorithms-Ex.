/**
 * 剑指offer第9题：变态跳台阶
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * @author Yu Yang
 * @create 2020-01-27 18:51
 */
public class Question_9 {

    /**
     * 思路：依然是斐波那契数列的变种。dp[n]=dp[0]+dp[1]+...+dp[n-1] = 2dp[n-1]
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        if (target <= 1) {
            return target;
        }
        int last = 1; // target=1时，有1种跳法。
        for (int i = 2; i <= target; i++) {
            last *= 2;
        }
        return last;
    }
}
