//给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aaabb", k = 3
//输出：3
//解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
// 
//
// 示例 2： 
//
// 
//输入：s = "ababbc", k = 2
//输出：5
//解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由小写英文字母组成 
// 1 <= k <= 105 
// 
// Related Topics 递归 分治算法 Sliding Window 
// 👍 340 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 滑动窗口：加一个限制条件，限制窗口中的非重复字符个数num，然后枚举所有的num，分别进行滑动窗口计数
    // 时间复杂度：O(26 * n)，26是小写字母字符集的大小
    public int longestSubstring(String s, int k) {
        int len = s.length();
        int result = 0;
        // 限制窗口内的非重复字符的个数为num，枚举所有的num，分别进行滑动窗口
        for (int num = 1; num <= 26; num++) {
            int left = 0, right = 0;
            int[] count = new int[26]; // 记录窗口内字符的出现次数
            int lessThanK = 0; // 窗口中小于 k 的字符的数目
            int totalNum = 0; // 窗口中非重复字符的个数
            while (right < len) {
                count[s.charAt(right) - 'a']++;
                if (count[s.charAt(right) - 'a'] == 1) {
                    lessThanK++;
                    totalNum++;
                }
                if (count[s.charAt(right) - 'a'] == k) {
                    lessThanK--;
                }
                // 如果窗口内非重复字符数目大于num了，则左边界右移
                while (totalNum > num) {
                    count[s.charAt(left) - 'a']--;
                    if (count[s.charAt(left) - 'a'] == k - 1) {
                        lessThanK++;
                    }
                    if (count[s.charAt(left) - 'a'] == 0) {
                        lessThanK--;
                        totalNum--;
                    }
                    left++;
                }
                if (lessThanK == 0) {
                    result = Math.max(result, right - left + 1);
                }
                right++;
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
