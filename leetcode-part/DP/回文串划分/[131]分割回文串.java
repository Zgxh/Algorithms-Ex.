//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 
//
// 返回 s 所有可能的分割方案。 
//
// 示例: 
//
// 输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//] 
// Related Topics 回溯算法 
// 👍 422 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP + dfs
    // dfs 把从 start 开始的子串分为两部分，利用第二部分来求解组合，然后把第一部分的回文串加入到求得的组合开头
    // DP 数组是为了减少回文串的判断次数
    private String s;
    private int len;
    private boolean[][] dp;

    public List<List<String>> partition(String s) {
        this.len = s.length();
        this.s = s;
        this.dp = new boolean[len][len];
        for (int interval = 0; interval < len; interval++) { // 子串的首尾间隔
            for (int i = 0; i < len - interval; i++) { // 子串的开始位置
                int j = i + interval; // 子串的结束位置
                if (s.charAt(i) == s.charAt(j) && (interval <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }

        return partitionHelp(0);
    }

    private List<List<String>> partitionHelp(int start) {
        List<List<String>> result = new ArrayList();
        if (start == s.length()) {
            result.add(new ArrayList());
            return result;
        }
        // 把从start开始的子串分为 start~start+i， start+i+1~len-1两段，求解第二段的所有组合，然后把第一段的回文串加入第二段返回的组合中的头部
        for (int i = 0; i < len - start; i++) {
            if (!dp[start][start + i]) { // 如果第一段不是回文串，直接返回
                continue;
            }
            List<List<String>> second = partitionHelp(start + i + 1);
            String first = s.substring(start, start + i + 1);
            for (List<String> list : second) {
                list.add(0, first); // 遍历所有的组合，把第一段回文串放入list中
                result.add(list);
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
