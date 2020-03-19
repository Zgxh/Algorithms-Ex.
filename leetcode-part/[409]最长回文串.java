//给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。 
//
// 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。 
//
// 注意: 
//假设字符串的长度不会超过 1010。 
//
// 示例 1: 
//
// 
//输入:
//"abccccdd"
//
//输出:
//7
//
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
// 
// Related Topics 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：因为只有字符，直接用数组来存，并以字符值作为index进行索引，避免使用Map降低效率。
     * 之后直接统计出现奇数次字符的个数，也是一个优化。
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {

        int[] charNum = new int[128];
        for (char ch : s.toCharArray()) {
            charNum[ch] += 1;
        }
        int count = 0;
        for (int num : charNum) {
            count += (num % 2); // 统计奇数字符出现的次数
        }
        return count == 0 ? s.length() : s.length() - count + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
