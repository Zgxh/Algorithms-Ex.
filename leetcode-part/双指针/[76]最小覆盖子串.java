//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 940 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 双指针
    public String minWindow(String s, String t) {
        // 对t中的元素进行统计
        int count = 0;
        int[] charCount = new int[128];
        for (char ch : t.toCharArray()) {
            charCount[ch - 'A']++;
        }
        for (int i = 0; i < 128; i++) {
            if (charCount[i] > 0) {
                count++;
            }
        }
        // 对双指针之间的s中的元素进行统计
        int index = 0;
        int[] sCount = new int[128];
        int sLen = s.length();
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int leftIndex = 0;
        while (right < sLen) {
            char ch = s.charAt(right);
            if (charCount[ch - 'A'] > 0) {
                sCount[ch - 'A']++;
                if (sCount[ch - 'A'] == charCount[ch - 'A']) {
                    index++;
                }
            }
            right++;
            // left 左移
            while (index == count) {
                if (right - left < minLen) {
                    minLen = right - left;
                    leftIndex = left;
                }
                char leftCh = s.charAt(left);
                if (charCount[leftCh - 'A'] > 0) {
                    sCount[leftCh - 'A']--;
                    if (sCount[leftCh - 'A'] < charCount[leftCh - 'A']) {
                        index--;
                    }
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(leftIndex, leftIndex + minLen);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
