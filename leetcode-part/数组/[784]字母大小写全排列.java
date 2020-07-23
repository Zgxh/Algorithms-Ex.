//给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。 
//
// 
//示例:
//输入: S = "a1b2"
//输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
//
//输入: S = "3z4"
//输出: ["3z4", "3Z4"]
//
//输入: S = "12345"
//输出: ["12345"]
// 
//
// 注意： 
//
// 
// S 的长度不超过12。 
// S 仅由数字和字母组成。 
// 
// Related Topics 位运算 回溯算法 
// 👍 191 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：队列。
     * 遍历S的所有字符，然后依次弹出上一个版本，并分别加入下一个字符的大小写版本。
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        Queue<StringBuilder> queue = new LinkedList();
        queue.offer(new StringBuilder());
        for (char ch : S.toCharArray()) {
            int len = queue.size();
            if (Character.isDigit(ch)) { // 如果是数字，只需要把数字加进去即可
                for (int i = 0; i < len; i++) {
                    StringBuilder temp = queue.poll();
                    temp.append(ch);
                    queue.offer(new StringBuilder(temp));
                }
            } else { // 如果是字母，则分别加入大小写字母
                for (int i = 0; i < len; i++) {
                    StringBuilder temp = queue.poll();
                    temp.append(Character.toLowerCase(ch));
                    queue.offer(new StringBuilder(temp));
                    temp.deleteCharAt(temp.length() - 1);
                    temp.append(Character.toUpperCase(ch));
                    queue.offer(new StringBuilder(temp));
                }
            }
        }
        List<String> result = new ArrayList();
        for (StringBuilder sb : queue) {
            result.add(sb.toString());
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
