//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 
//
// 示例 1： 
//
// 输入: "the sky is blue"
//输出: "blue is sky the"
// 
//
// 示例 2： 
//
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 
//
// 说明： 
//
// 
// 无空格字符构成一个单词。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 
//
// 进阶： 
//
// 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。 
// Related Topics 字符串


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路1：API杀手
     * @param s
     * @return
     */
    public String reverseWords(String s) {

        s = s.trim();
        String[] words = s.split("\\s+");
        List<String> list = Arrays.asList(words);
        Collections.reverse(list);

        return String.join(" ", list);
    }

    /**
     * 思路2：自己实现
     * @param s
     * @return
     */
    public String reverseWords1(String s) {

        s += " ";
        List<String> list = new ArrayList<>();
        int start = -1, end = -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (start != -1) {
                    list.add(s.substring(start, end + 1));
                }
                start = -1;
                end = -1;
            } else {
                if (start == -1) {
                    start = i;
                }
                end = i;
            }
        }

        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();
        for (String word : list) {
            sb.append(word + " ");
        }

        return sb.length() == 0 ? "" : sb.deleteCharAt(sb.length() - 1).toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
