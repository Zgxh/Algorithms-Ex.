//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法 
// 👍 883 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：回溯法。
     */

    List<String> result;
    Map<Character, String> map;

    public List<String> letterCombinations(String digits) {
        result = new ArrayList();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        map = new HashMap();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        recursion(new StringBuilder(), 0, digits);

        return result;
    }

    private void recursion(StringBuilder temp, int index, String digits) {
        if (index == digits.length()) {
            result.add(temp.toString());
            return;
        }
        for (char ch : map.get(digits.charAt(index)).toCharArray()) {
            temp.append(ch);
            recursion(temp, index + 1, digits);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
