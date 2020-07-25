//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。 
//
// 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。 
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"ae
//c"不是）。 
//
// 示例 1: 
//s = "abc", t = "ahbgdc" 
//
// 返回 true. 
//
// 示例 2: 
//s = "axc", t = "ahbgdc" 
//
// 返回 false. 
//
// 后续挑战 : 
//
// 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码
//？ 
//
// 致谢: 
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。 
// Related Topics 贪心算法 二分查找 动态规划 
// 👍 228 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     public boolean isSubsequence(String s, String t) {
         int i = 0;
         for (char ch : s.toCharArray()) {
             while (i < t.length() && t.charAt(i) != ch) {
                 i++;
             }
             i++;
         }

         return i <= t.length();
     }

    // 当查询非常多的时候，应该先处理t字符串，找到所有index的下一个字符出现的位置
    public boolean isSubsequence1(String s, String t) {
        int len = t.length();
        int[][] dp = new int[len + 1][26]; // 存放当前index的下一个小写字母出现的位置
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        for (int i = 0; i < len; i++) {
            int letter = t.charAt(i) - 'a';
            for (int j = i; j >= 0 && dp[j][letter] == -1; j--) {
                dp[j][letter] = i + 1; // char在t中的位置与char在dp数组中的位置差 1
            }
        }
        int lastIndex = 0;
        for (char ch : s.toCharArray()) {
            lastIndex = dp[lastIndex][ch - 'a'];
            if (lastIndex == -1) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
