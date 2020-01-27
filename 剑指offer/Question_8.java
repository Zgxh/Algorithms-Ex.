/**
 * 剑指offer第8题：跳台阶
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 *
 * @author Yu Yang
 * @create 2020-01-27 18:37
 */
public class Question_8 {

    /**
     * 思路：实质是斐波那契数列。青蛙想跳到target阶，可以从target-1阶跳1阶，或者从target-2阶跳2阶；
     * 即：dp[i] = dp[i-1] + dp[i-2]
     * @param target
     * @return
     */
    public int JumpFloor(int target) {
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int lastButTwo = 1;
        int last = 2;
        for (int i = 3; i <= target; i++) {
            int temp = lastButTwo;
            lastButTwo = last;
            last += temp;
        }
        return last;
    }
}
