//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3168 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 方法 1：DP 解法
     * O(n^2)
     * 状态转移方程: dp[i][j] = (s[i] == s[j]) && dp[i + 1][j - 1]
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        String result = "";
        // interval 代表 j - i，即字符串首尾index的跨度
        for (int interval = 0; interval < len; interval++) {
            // i, j 分别代表字符串的首尾 index
            for (int i = 0; i + interval < len; i++) {
                int j = i + interval;
                if (interval == 0) {
                    dp[i][j] = true;
                } else if (interval == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                // 如果[i,j]内的当前子串长度大于result的长度，则更新result为当前子串
                if (dp[i][j] && interval + 1 > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 方法 2：
     *     中心扩展算法： O(n ^ 2)
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        int maxLen = 0;
        int strLeft = -1, strRight = -1; // 结果回文串对应的起始位置
        for (int i = 0; i < len; i++) {
            int oddLen = centerExpand(s, i, i); // 奇数长度
            int evenLen = centerExpand(s, i, i + 1); // 偶数长度
            int curLen = Math.max(oddLen, evenLen);
            if (curLen > maxLen) {
                strLeft = i - (curLen - 1) / 2;
                strRight = i + curLen / 2;
                maxLen = strRight - strLeft + 1;
            }
        }

        return s.substring(strLeft, strRight + 1);
    }
    // 利用中心扩展算法求解当前中心对应的最长回文子串
    private int centerExpand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1; // 实际的回文子串为 [left + 1, right - 1]
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 方法3：马拉车算法 Manacher
     * O(n)
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        // 在相邻的字符中间插入 '#'，让所有回文子串都变成奇数长度，于是下面的所有处理都按奇数长度的回文子串来进行
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (int i = 0; i < len; i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        // 更新s和len
        s = sb.toString();
        len = s.length();
        int start = 0, end = 0; // 保存结果回文子串对应的 左右边界
        List<Integer> armLen = new ArrayList(); // 记录回文串的臂展
        int right = -1, j = -1; // 记录之前的回文串中右边界最远的那者：right是该回文串的右边界，j是该回文串的中心
        for (int i = 0; i < len; i++) {
            int curArmLen = 0;
            if (right >= i) {
                // 求 i 关于 j 的对称位置
                int iSymmetry = 2 * j - i;
                // 确定当前 i 位置的最小臂展，小于该臂展的部分不用判断
                int minArmLen = Math.min(armLen.get(iSymmetry), right - i);
                curArmLen = centerExpand(s, i - minArmLen, i + minArmLen);
            } else {
                curArmLen = centerExpand(s, i, i);
            }
            armLen.add(curArmLen);
            // 更新 right 和 j 为右边界最远的回文串
            if (i + curArmLen > right) {
                right = i + curArmLen;
                j = i;
            }
            // 出现了更长的回文串，更新结果
            if (2 * curArmLen + 1 > end - start + 1) {
                start = i - curArmLen;
                end = i + curArmLen;
            }
        }
        // 删掉 '#'，得到结果
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) != '#') {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }
    // 使用中心扩展法计算，返回的是臂长
    private int centerExpand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return (right - left - 1) / 2; // 对应的回文子串为 [left + 1, right - 1]
    }

}
//leetcode submit region end(Prohibit modification and deletion)
