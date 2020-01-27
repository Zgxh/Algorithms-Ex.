/**
 * 剑指offer第10题：矩形覆盖
 *
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * @author Yu Yang
 * @create 2020-01-27 18:59
 */
public class Question_10 {

    /**
     * 思路：小矩形1个横着可以占1个位置，2个竖着可以占两个位置。
     * dp[n] = dp[n-2] + dp[n-1].  2个小矩形同时竖着或横着都能占2个位置，但横着的情况包含在了【dp[n-1]加1块横着的】中。
     * 上述公式代表：dp[n-2]是n-2的时候放2个竖着的,dp[n-1]代表n-1的时候放1个横着的。
     * @param target
     * @return
     */
    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        int lastButTwo = 1;
        int last = 2;
        for (int i = 3; i <= target; i++) {
            int temp = lastButTwo;
            lastButTwo = last;
            last = temp + last;
        }
        return last;
    }
}
