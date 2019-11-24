//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
//输出: false
// 
// Related Topics 双指针 字符串



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：双指针左右往中间逼近。不是数字或字母的话，往中间移动指针；
     * 判断时注意忽略大小写；
     * 还要特别注意左右分别为字母和数字的情况，可能差也为32，不要错误判断为大小写！
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {

        if (s == null) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !(s.charAt(left) >= 'A' && s.charAt(left) <= 'Z'
                    || s.charAt(left) >= 'a' && s.charAt(left) <= 'z'
                    || s.charAt(left) >= '0' && s.charAt(left) <= '9')) {
                left++;
            }
            while (left < right && !(s.charAt(right) >= 'A' && s.charAt(right) <= 'Z' ||
                    s.charAt(right) >= 'a' && s.charAt(right) <= 'z' ||
                    s.charAt(right) >= '0' && s.charAt(right) <= '9')) {
                right--;
            }
            if (left < right) {
                // 如果忽略大小写后对应,注意数字和字母的差也可能为32
                if (s.charAt(left) == s.charAt(right)
                        || !(s.charAt(left) >= '0' && s.charAt(left) <= '9'
                        || s.charAt(right) >= '0' && s.charAt(right) <= '9')
                        && Math.abs(Integer.parseInt("" + (s.charAt(left) - s.charAt(right)))) == 32) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
