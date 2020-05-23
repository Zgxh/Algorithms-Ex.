//给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。 
//
// 示例： 
//
// 输入: S = "ADOBECODEBANC", T = "ABC"
//输出: "BANC" 
//
// 说明： 
//
// 
// 如果 S 中不存这样的子串，则返回空字符串 ""。 
// 如果 S 中存在这样的子串，我们保证它是唯一的答案。 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：双指针滑动窗口。 right用来增大窗口，left用于缩小窗口。
     * 当窗口字符不够的时候，right++；
     * 当窗口字符够了，可以减小的时候，left++；
     * 遍历一遍得到最小长度的字符串。
     */
    Map<Character, Integer> map;
    Map<Character, Integer> windowCount;

    public String minWindow(String s, String t) {

        String result = "";
        map = new HashMap<>();
        windowCount = new HashMap<>();

        for (char ch : t.toCharArray()) { // 初始化map，统计t的每个字符个数
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int left = 0, right = -1;
        int minLength = Integer.MAX_VALUE;
        while (right < s.length()) {
            right++;
            if (right < s.length() && map.containsKey(s.charAt(right))) {
                windowCount.put(s.charAt(right), windowCount.getOrDefault(s.charAt(right), 0) + 1);
            }
            while (left <= right && moreThanT()) {
                while (left < right && !map.containsKey(s.charAt(left))) {
                    left++;
                }
                if (right - left + 1 < minLength) {
                    result = s.substring(left, right + 1);
                    minLength = right - left + 1;
                }
                if (map.containsKey(s.charAt(left))) {
                    windowCount.put(s.charAt(left), windowCount.getOrDefault(s.charAt(left), 0) - 1);
                }
                left++;
            }
        }

        return result;
    }

    /**
     * 判断窗口是否够了元素。
     * @return
     */
    private boolean moreThanT() {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (windowCount.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
