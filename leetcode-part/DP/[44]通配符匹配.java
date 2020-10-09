//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
// 
//
// 两个字符串完全匹配才算匹配成功。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
// 
//
// 示例 3: 
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
// 
//
// 示例 4: 
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
// 
//
// 示例 5: 
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false 
// Related Topics 贪心算法 字符串 动态规划 回溯算法 
// 👍 530 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // // DP 解法
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1]; // dp数组中的i和j对应字符串index的i-1,j-1
        // 初始化当s的子串长度为0的时候
        dp[0][0] = true;
        for (int i = 1; i <= pLen; i++) {
            if (p.charAt(i - 1) == '*') { // 对于s的index = 0时，只有p前面全是*时才会是
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                // 如果当前位置j是'*'，可以选择用还是不用
                // 如果不用，则判断p串0~j-1能否匹配s的0~i
                // 如果用，则可能'*'不仅匹配了当前位，还匹配了前面的位
                // 第二个条件指j位置的'*'从i才开始匹配，第三个条件则指j位置的'*'从i之前就已经被使用了
                // 其中第三个条件覆盖了第二个条件，第二个条件其实可以省略
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[sLen][pLen];
    }

//     // 递归方式，使用hashMap来避免重复的计算
//     private Map<String, Boolean> cache; // 缓存，避免重复的判断
//     private int sLen;
//     private int pLen;
//     private String s;
//     private String p;

//     public boolean isMatch(String s, String p) {
//         this.cache = new HashMap();
//         this.sLen = s.length();
//         this.pLen = p.length();
//         this.s = s;
//         this.p = p;

//         return match(0, 0);
//     }

//     private boolean match(int sIndex, int pIndex) {
//         if (sIndex == sLen && pIndex == pLen) {
//             return true;
//         } else if (pIndex == pLen) {
//             return false;
//         } else if (sIndex == sLen) {
//             return p.substring(pIndex).split("\\*").length == 0;
//         }
//         if (cache.containsKey(sIndex + "#" + pIndex)) {
//             return cache.get(sIndex + "#" + pIndex);
//         }
//         boolean result = false;
//         if (p.charAt(pIndex) == '?' || s.charAt(sIndex) == p.charAt(pIndex)) {
//             result = result || match(sIndex +1, pIndex + 1);
//         } else if (p.charAt(pIndex) == '*') {
//             result = result || match(sIndex + 1, pIndex) || match(sIndex, pIndex + 1);
//         }
//         cache.put(sIndex + "#" + pIndex, result);

//         return result;
//     }
}
//leetcode submit region end(Prohibit modification and deletion)
