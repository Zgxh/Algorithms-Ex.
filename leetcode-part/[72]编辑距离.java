//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 动态规划。要把word1变成word2，和把word2变成word1是一样的。
     * 题目说：对word1的操作可以有：插入、删除、替换。则对word1的删除可以看成是对word2的插入。
     * 所以等价操作就是：（1）对word1插入；（2）对word2插入；（3）对word1替换。
     * 自顶向下看，（1）就是假定word2只有前n-1个字符，word1到只含前n-1个字符的word2的变换次数，再加上最后word1加入Word2最后一个字符；
     * （2）假定word1只有前n-1字符，只含n-1字符的word1到word2的变换次数，再加上最后word2插入word1最后一个字符；
     * （3）word1和word2都只考虑前n-1字符，最后用变换次数再加上word1第n个字符的替换操作（有可能本来就相同，就不用换）。
     * 自顶向下的递归可以看成是自底向上的【动态规划】，边界是word1或者word2为空，则操作数就是最大字符串的长度；
     * dp方程就是dp[i][j] = min(dp[i-1][j]+1. dp[i][j-1]+1, dp[i-1][j-1]+1)，分别对应1、2、3；
     * 其中当word1和word2的下一个字符相同时，第三项不用替换，即不用+1.
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        int length1 = word1.length(), length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];

        // 初始化边界
        for (int i = 0; i < length1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < length2 + 1; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < length1 + 1; i++) {
            for (int j = 1; j < length2 + 1; j++) {
                int insert1 = dp[i - 1][j] + 1; // word1后面插一个字符
                int insert2 = dp[i][j - 1] + 1; // word2后面插一个字符
                int modify1 = word1.charAt(i - 1) == word2.charAt(j - 1) ?
                        dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1; // word1下一个字符修改为word2的字符
                dp[i][j] = Math.min(Math.min(insert1, insert2), modify1);
            }
        }

        return dp[length1][length2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
