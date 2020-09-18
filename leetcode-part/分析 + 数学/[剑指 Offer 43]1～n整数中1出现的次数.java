//输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。 
//
// 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。 
//
// 
//
// 示例 1： 
//
// 输入：n = 12
//输出：5
// 
//
// 示例 2： 
//
// 输入：n = 13
//输出：6 
//
// 
//
// 限制： 
//
// 
// 1 <= n < 2^31 
// 
//
// 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/ 
// Related Topics 数学 
// 👍 89 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 数学分析法。
     * 把当前数字分成三部分：高位部分 high、当前位 cur、低位部分 low。
     * 对当前位分为三种情况：1.cur=0   2.cur=1   3.cur>1
     * 从右至左遍历当前位 cur，求所有情况的加和。
     * 题解地址：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int digit = 1;
        int result = 0;
        int high = n / 10, low = 0, cur = n % 10;
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                result += high * digit;
            } else if (cur == 1) {
                result += high * digit + (low + 1);
            } else {
                result += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
