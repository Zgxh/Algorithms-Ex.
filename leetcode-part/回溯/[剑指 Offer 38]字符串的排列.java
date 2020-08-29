//输入一个字符串，打印出该字符串中字符的所有排列。 
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 回溯算法 
// 👍 97 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 全排列问题，回溯法。
     * 注意在回溯的过程中过滤掉出现重复值的情况。
     */

    private List<String> list;
    private boolean[] visited;
    private int len;

    public String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        list = new ArrayList<>();
        len = s.length();
        visited = new boolean[len];
        char[] s2Arr = s.toCharArray();
        Arrays.sort(s2Arr);
        recursion(s2Arr, 0, new StringBuilder());

        return list.toArray(new String[0]);
    }

    private void recursion(char[] s, int index, StringBuilder sb) {
        if (index == len) {
            list.add(sb.toString());
            return;
        }
        int lastVisitHere = -1; // 避免重复的结果，对sb的同一个位置，不放置已经遍历过的（重复值）情形
        for (int i = 0; i < len; i++) {
            if (!visited[i] && (i == 0 || i > 0 && s[i] != lastVisitHere)) {
                visited[i] = true;
                sb.append(s[i]);
                lastVisitHere = s[i];
                recursion(s, index + 1, sb);
                sb.deleteCharAt(index);
                visited[i] = false;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
