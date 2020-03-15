//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，
// 使得句子中所有的单词都在词典中。返回所有这些可能的句子。
//
// 说明： 
//
// 
// 分隔时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// 示例 2： 
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
// 
// Related Topics 动态规划 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：以139题为基础，先确定dp数组，dp[i]代表s的第i个位置之前的子串是否能被分成wordDict中的串集。
     * 然后采用递归树，
     */

    private List<String> result = new LinkedList<>();
    private LinkedList<String> words = new LinkedList<>();
    private Set<String> wordSet = new HashSet<>();

    public List<String> wordBreak(String s, List<String> wordDict) {

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        // List转Set
        for (String str : wordDict) {
            wordSet.add(str);
        }

        // 建dp数组
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        if (dp[len]) {
            dfs(s, len - 1, dp);
        }
        return result;
    }

    private void dfs(String s, int end, boolean[] dp) {
        if (wordSet.contains(s.substring(0, end + 1))) {
            words.addFirst(s.substring(0, end + 1));
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word + " ");
            }
            sb.deleteCharAt(sb.length() - 1); // 删掉最后一个空格
            result.add(sb.toString());
            words.removeFirst();
        }
        for (int i = 1; i < end; i++) {
            if (dp[i]) {
                String suffix = s.substring(i, end + 1);
                if (wordSet.contains(suffix)) {
                    words.addFirst(suffix);
                    dfs(s, i - 1, dp);
                    words.removeFirst();
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
