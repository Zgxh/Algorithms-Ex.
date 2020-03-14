//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。 
//
// 说明： 
//
// 
// 拆分时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
// 
//
// 示例 2： 
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
// Related Topics 动态规划


import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：动态规划。dp数组i位置记录string的前i个字符的子串能不能被wordDict分割。
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null) {
            return false;
        }
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        int maxLength = 0; // maxLength记录wordDict中最大字符串长度，避免后面多余的判断。
        for (int i = 0; i < wordDict.size(); i++) {
            if (wordDict.get(i).length() > maxLength) {
                maxLength = wordDict.get(i).length();
            }
        }
        for (int i = 1; i <= len; i++) {
            for (int j = Math.max(0, i - maxLength); j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
