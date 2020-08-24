//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4210 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：滑动窗口。
     * 利用hashset来存放[left, right]之间的字符，right不断右移，
     * 当right位置的字符ch已经在hashset中时，left右移直到越过ch,
     * 同时删除hashset中经过的left的值。
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int left = 0;
        Set<Character> set = new HashSet(); // 这里要用hashset，不能用字典数组，因为可能包含除了小写字母以外的字符
        int maxLen = 0;
        for (int right = 0; right < len; right++) {
            char ch = s.charAt(right);
            if (set.contains(ch)) {
                while (left < right && s.charAt(left) != ch) { // 找到与right位置相同的最左位置
                    set.remove(s.charAt(left));
                    left++;
                }
                // 跳过最左位置
                set.remove(s.charAt(left));
                left++;
            }
            set.add(ch);
            maxLen = Math.max(maxLen, right - left + 1); // 此时的子串一定是符合要求的，直接更新最长子串的长度
        }

        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
