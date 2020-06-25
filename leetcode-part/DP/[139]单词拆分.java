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


import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：dp问题。
     * 使用dp数组存放前i个字符的子串能否被字典中的字符串成功分割。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        int maxLength = 0; // 提前判断wordDict中字符串的最大长度，避免多余计算
        Set<String> wordSet = new HashSet<>(); // 使用哈希表加快查找速度
        for (String word : wordDict) {
            wordSet.add(word);
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }

        int len = s.length();
        boolean[] dp = new boolean[len + 1]; // dp[i] 代表 0 ~ i-1 的子串能否被字典里的字符串分割
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = Math.max(0, i - maxLength); j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) { // 0~j-1 和 j~i-1 都能被成功分割
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
