//给定一个字符串列表，你可以将这些字符串连接成一个循环字符串，对于每个字符串，你可以选择是否翻转它。在所有可能的循环字符串中，你需要分割循环字符串（这将使循环
//字符串变成一个常规的字符串），然后找到字典序最大的字符串。 
//
// 具体来说，要找到字典序最大的字符串，你需要经历两个阶段： 
//
// 
// 将所有字符串连接成一个循环字符串，你可以选择是否翻转某些字符串，并按照给定的顺序连接它们。 
// 在循环字符串的某个位置分割它，这将使循环字符串从分割点变成一个常规的字符串。 
// 
//
// 你的工作是在所有可能的常规字符串中找到字典序最大的一个。 
//
// 示例: 
//
// 输入: "abc", "xyz"
//输出: "zyxcba"
//解释: 你可以得到循环字符串 "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-"，
//其中 '-' 代表循环状态。 
//答案字符串来自第四个循环字符串， 
//你可以从中间字符 'a' 分割开然后得到 "zyxcba"。
// 
//
// 
//
// 注意: 
//
// 
// 输入字符串只包含小写字母。 
// 所有字符串的总长度不会超过 1,000。 
// 
//
// 
// Related Topics 字符串 
// 👍 20 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目要求：按照字符串给定的顺序依次连接，但每个字符串可以反转自身
    // 原则：如果一个字符串没有被作为循环字符串的起点，则直接比较其本身和反转后哪个字典序更大
    public String splitLoopedString(String[] strs) {
        int strsLen = strs.length;
        // 更新 strs 数组，判断 strs[i] 自身与反转后哪个更大
        for (int i = 0; i < strsLen; i++) {
            String reversed = new StringBuilder(strs[i]).reverse().toString();
            if (reversed.compareTo(strs[i]) > 0) {
                strs[i] = reversed;
            }
        }
        // 遍历每个字符串当头的情况
        String result = "";
        for (int i = 0; i < strsLen; i++) {
            String str = strs[i];
            String reversedStr = new StringBuilder(str).reverse().toString();
            // 以 strs[i] 打头，然后把剩余的非打头的子串直接全部连接
            StringBuilder combinedRemain = new StringBuilder();
            for (int j = i + 1; j < strsLen; j++) {
                combinedRemain.append(strs[j]);
            }
            for (int j = 0; j < i; j++) {
                combinedRemain.append(strs[j]);
            }
            // 以 strs[i] 或者其反转打头，遍历所有的分割情况，寻找最大的字典序排列
            for (int k = 0; k < str.length(); k++) {
                String strCombined = str.substring(k, str.length()) + combinedRemain + str.substring(0, k);
                if (strCombined.compareTo(result) > 0) {
                    result = strCombined;
                }
            }
            for (int k = 0; k < reversedStr.length(); k++) {
                String revStrCombined = reversedStr.substring(k, reversedStr.length()) + combinedRemain + reversedStr.substring(0, k);
                if (revStrCombined.compareTo(result) > 0) {
                    result = revStrCombined;
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
