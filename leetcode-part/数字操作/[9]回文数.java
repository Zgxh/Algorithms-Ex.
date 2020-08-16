//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。 
//
// 示例 1: 
//
// 输入: 121
//输出: true
// 
//
// 示例 2: 
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3: 
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 进阶: 
//
// 你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学 
// 👍 1180 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 题目不让用字符串，则直接用数字操作。
     * 把x分成左右两份，其中右半部分反转。
     *
     * 时间复杂度：O(lg n),每次除10
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        int rightHalf = 0;
        while (x > rightHalf) {
            rightHalf = rightHalf * 10 + x % 10;
            x /= 10;
        }
        // 分奇数长度和偶数长度，奇数长度下，rightHalf比左半部分多一位
        return x == rightHalf || x == rightHalf / 10;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
