//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。 
//
// 
//
// 示例: 
//
// 输入: "25525511135"
//输出: ["255.255.11.135", "255.255.111.35"] 
// Related Topics 字符串 回溯算法 
// 👍 310 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：回溯法。
     *
     */

    private List<String> result = new ArrayList();
    private String s;

    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        recursion(new StringBuilder(), 0, 0);

        return result;
    }

    public void recursion(StringBuilder sb, int begin, int location) { // begin是指字符在字符串s中的index，location是ip中的第几段
        if (location == 3) {
            if ((s.charAt(begin) == '0' && begin < s.length() - 1) || s.length() - begin > 3) {
                return;
            }
            String curSubstring = s.substring(begin);
            if (Integer.parseInt(curSubstring) <= 255) { // 前面应该防止int越界
                sb.append("." + curSubstring);
                result.add(sb.toString());
                sb.delete(begin + 3, sb.length());
            }
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (begin + i >= s.length() || (s.charAt(begin) == '0' && i >= 2)) { // 多位数不允许首位数是 0
                return;
            }
            String curSubstring = s.substring(begin, begin + i);
            if (Integer.parseInt(curSubstring) <= 255) {
                if (location > 0) { // 除了第一个位置，都应该加一个 '.'
                    sb.append(".");
                }
                sb.append(curSubstring);
                recursion(sb, begin + i, location + 1);
                sb.delete(begin + location, sb.length());
                if (location > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.restoreIpAddresses("25525511135");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
