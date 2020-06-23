//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 考虑到如果先转为十进制，然后相加后再转回二进制，可能超出Integer的范围。因此：
     *
     * 思路：从后往前，按位操作，对应位相加，再加上进位值，然后对二取余即为当前位的值；
     * 除二取整即为进位值。
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if (a.length() < b.length()) { // 保证a是长串
            String temp = a;
            a = b;
            b = temp;
        }
        int aLen = a.length(), bLen = b.length();
        int carry = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bLen; i++) {
            int temp = a.charAt(aLen - 1 - i) - '0' + b.charAt(bLen - 1 - i) - '0' + carry;
            result.append(temp % 2);
            carry = temp / 2;
        }
        for (int i = bLen; i < aLen; i++) {
            int temp = a.charAt(aLen - 1 - i) - '0' + carry;
            result.append(temp % 2);
            carry = temp / 2;
        }
        if (carry == 1) {
            result.append(carry);
        }
        return result.reverse().toString(); // 最后对字符串进行反转
    }
}
//leetcode submit region end(Prohibit modification and deletion)
