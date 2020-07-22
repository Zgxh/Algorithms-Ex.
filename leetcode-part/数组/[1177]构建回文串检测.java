//给你一个字符串 s，请你对 s 的子串进行检测。 
//
// 每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。我们可以 重新排列 子串 s[left], ..., s[ri
//ght]，并从中选择 最多 k 项替换成任何小写英文字母。 
//
// 如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。 
//
// 返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。 
//
// 注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，也就是说，如果 s[left..right] = "aaa" 且 k = 2，我们只能替换
//其中的两个字母。（另外，任何检测都不会修改原始字符串 s，可以认为每次检测都是独立的） 
//
// 
//
// 示例： 
//
// 输入：s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
//输出：[true,false,false,true,true]
//解释：
//queries[0] : 子串 = "d"，回文。
//queries[1] : 子串 = "bc"，不是回文。
//queries[2] : 子串 = "abcd"，只替换 1 个字符是变不成回文串的。
//queries[3] : 子串 = "abcd"，可以变成回文的 "abba"。 也可以变成 "baab"，先重新排序变成 "bacd"，然后把 "cd" 
//替换为 "ab"。
//queries[4] : 子串 = "abcda"，可以变成回文的 "abcba"。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, queries.length <= 10^5 
// 0 <= queries[i][0] <= queries[i][1] < s.length 
// 0 <= queries[i][2] <= s.length 
// s 中只有小写英文字母 
// 
// Related Topics 数组 字符串 
// 👍 27 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：利用数组记录s的i位置前所有字符出现的次数，在子串中统计单个字符出现的次数，看给
     * 定的额外字符是否能消除单个。
     */

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> result = new ArrayList<>();
        int len = s.length();
        char[] sArray = s.toCharArray();
        int[][] lettersCount = new int[len][26];
        lettersCount[0][sArray[0] - 'a'] = 1;
        for (int i = 1; i < len; i++) {
            lettersCount[i][sArray[0] - 'a'] = 1;
            for (int j = 0; j < 26; j++) {
                lettersCount[i][j] += lettersCount[i - 1][j];
            }
        }
        for (int[] query : queries) {
            int count = 0;
            for (int i = 0; i < 26; i++) {
                int startValue = query[0] == 0 ? 0 : lettersCount[query[0] - 1][i];
                count += (lettersCount[query[1]][i] - startValue) % 2;
            }
            result.add(query[2] >= count / 2);
        }

        return result;
    }

    /**
     * 改进版本。利奇偶性与二进制之间的关系，把奇偶转化成二进制，利用二进制来代替数组
     * 进行计数。
     */
    public List<Boolean> canMakePaliQueries1(String s, int[][] queries) {
        ArrayList<Boolean> result = new ArrayList<>();
        int len = s.length();
        int[] counts = new int[len];
        int count = 0;
        for (int i = 0; i < len; i++) { // int 占4字节，大于26位，可以用来存落单字符的个数
            count ^= 1 << (s.charAt(i) - 'a');
            counts[i] = count;
        }
        for (int[] query : queries) {
            int start = query[0] == 0 ? 0 : counts[query[0] - 1];
            int subCount = counts[query[1]] ^ start; // 位运算代替减法
            int single = 0;
            while (subCount != 0) { // 统计所有二进制位上的1的个数，即落单的字符
                single += (subCount & 1);
                subCount >>= 1;
            }
            result.add(query[2] >= single / 2);
        }

        return result;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
