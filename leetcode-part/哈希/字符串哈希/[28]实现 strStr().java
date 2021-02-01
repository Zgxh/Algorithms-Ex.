//实现 strStr() 函数。 
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。 
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
// Related Topics 双指针 字符串 
// 👍 555 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 字符串哈希。
     * 把主串（子串）与模式串进行哈希编码，然后比较哈希值。
     * 前提是实现哈希值的唯一性。
     */
    public int strStr(String haystack, String needle) {
        int len1 = haystack.length(), len2 = needle.length();
        if (len2 == 0) {
            return 0;
        } else if (len1 == 0 || len2 > len1) {
            return -1;
        }
        long mod = (long) 1e9 + 7;
        // 初始化模式串与首个子串的哈希值
        long hashSubA = 0, hashB = 0;
        int base = 26; // 把字符串编码成 base 进制的数，并求出对应的 10 进制的结果，字符串中小 index 对应高位
        for (int i = 0; i < len2; i++) {
            hashB = (hashB * base + needle.charAt(i) - 'a') % mod;
            hashSubA = (hashSubA * base + haystack.charAt(i) - 'a') % mod;
        }
        if (hashSubA == hashB) {
            return 0;
        }
        // 求出要被淘汰的最高位对应的基底，用于后面对 hashB 的更新
        long highestBase = 1;
        for (int i = 1; i <= len2; i++) {
            highestBase = (highestBase * base) % mod;
        }
        // 遍历首个字符的位置，找到匹配的子串
        for (int i = 1; i <= len1 - len2; i++) {
            // 相邻子串哈希的转换公式
            hashSubA = (hashSubA * base - highestBase * (haystack.charAt(i - 1) - 'a') + (haystack.charAt(i + len2 - 1) - 'a')) % mod;
            if (hashSubA == hashB) {
                return i;
            }
        }

        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
