//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，
//等等。 
//
// 请写一个函数，求任意第n位对应的数字。 
//
// 
//
// 示例 1： 
//
// 输入：n = 3
//输出：3
// 
//
// 示例 2： 
//
// 输入：n = 11
//输出：0 
//
// 
//
// 限制： 
//
// 
// 0 <= n < 2^31 
// 
//
// 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/ 
// Related Topics 数学 
// 👍 60 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：分析法。
     * 把 n 分段，0-9第一段共10个数，11-99第二段共90个数，100-999第三段共900个数...
     * 1. 从第二段往后，每次分段数字总数变为之前的10倍，第一段除外。
     * 2. 分段依据是数字的位数，每段比前一段位数增加 1，代表每个数字占几位。
     * 按照这个规则，先确定 n 在哪个分段，然后算出 n 到该分段首个数的偏移量来确定目标数字，
     * 再通过余数来进一步确定 n 在目标数字的哪一位。
     */
    public int findNthDigit(int n) {
        long base = 2, digit = 90;
        if (n < 10) {
            return n;
        }
        // 首段为10个数字，单独处理
        n -= 10;
        long begin = 10;
        // 确定 n 的分段
        while (n / (digit * base) != 0) {
            n -= digit * base;
            digit *= 10;
            begin *= 10;
            base++;
        }
        // 求偏移量来确定目标数字
        long shift = n / base;
        begin += shift;
        // 利用余数来确定该位是目标数字的哪一位
        int remainder = (int) (n % base);
        String target = begin + "";

        return Integer.valueOf(target.charAt(remainder) + "");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
