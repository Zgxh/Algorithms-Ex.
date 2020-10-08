//给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。 
//
// 下图是字符串 s1 = "great" 的一种可能的表示形式。 
//
//     great
//   /    \
//  gr    eat
// / \    /  \
//g   r  e   at
//           / \
//          a   t
// 
//
// 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。 
//
// 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。 
//
//     rgeat
//   /    \
//  rg    eat
// / \    /  \
//r   g  e   at
//           / \
//          a   t
// 
//
// 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。 
//
// 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。 
//
//     rgtae
//   /    \
//  rg    tae
// / \    /  \
//r   g  ta  e
//       / \
//      t   a
// 
//
// 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。 
//
// 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。 
//
// 示例 1: 
//
// 输入: s1 = "great", s2 = "rgeat"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s1 = "abcde", s2 = "caebd"
//输出: false 
// Related Topics 字符串 动态规划 
// 👍 155 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * DP写法
     * 采用三维dp数组，dp[i][j][len]表示s1中从i开始长度为len的子串能否
     * 被s2中以j开始长度为len的子串匹配。
     * 枚举字符串的所有长度k，并枚举str1与str2的所有起点，对str1和str2采用
     * 同样的长度k来截取子串，判断这两个子串是否能匹配为扰乱字符串：
     * 判断子串是否匹配时，枚举所有的划分位置，并分为两种情况：
     * 1. str1前段与str2前段匹配，后段与后段匹配；
     * 2. str1前段与str2后段匹配，后端与前段匹配。
     *
     * 四层for循环，时间复杂度 O(N^4)；
     * 采用了三维dp数组，空间复杂度 O(N^3)
     */

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int len = s1.length();
        char[] str1 = s1.toCharArray(), str2 = s2.toCharArray();
        boolean[][][] dp = new boolean[len][len][len + 1];
        // dp初始化：单个字符 len = 1
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j][1] = (str1[i] == str2[j]);
            }
        }
        // dp计算，枚举所有的子串，计算是否存在一种划分，使两个长度相同的子串匹配为扰乱字符串
        // 只要在长度为k的子串中，存在任一种划分，可以使str1和str2匹配为扰乱字符串即可
        for (int k = 2; k <= len; k++) { // 枚举被判断的子串长度
            for (int i = 0; i <= len - k; i++) { // 枚举str1的开始位置
                for (int j = 0; j <= len - k; j++) { // 枚举str2的开始位置
                    for (int m = 1; m < k; m++) { // 枚举子串中的划分位置
                        // str1 的前段 与 str2 的前段 匹配，或，str1的前段 与 str2 的后段匹配
                        if ((dp[i][j][m] && dp[i + m][j + m][k - m])
                                || (dp[i][j + k - m][m] && dp[i + m][j][k - m])) {
                            dp[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][0][len];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
