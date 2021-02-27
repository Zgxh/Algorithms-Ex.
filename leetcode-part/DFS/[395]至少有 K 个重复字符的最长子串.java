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
// 👍 342 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 分治法递归：遍历一段字符串，如果某个字符出现次数小于k，则包含该字符ch的子串一定不会满足题意，
    // 因此以ch为分割点分割字符串，然后在子串中继续重复这个过程，直到某子串所有字符出现次数都>=k
    // 时间复杂度分析：每个递归循环要遍历一遍字符串 O(n)，但每个循环都会去掉一个不足k的字符，
    // 因此递归深度最大为26，即为字符集的容量，所以时间复杂度 O(26 * n)
    public int longestSubstring(String s, int k) {
        int len = s.length();
        if (k == 1) {
            return len;
        }

        return dfs(s, 0, s.length() - 1, k);
    }

    private int dfs(String s, int left, int right, int k) {
        if (k > right - left + 1) {
            return 0;
        }
        // 统计子串[left, right]中每个字符的出现次数
        int[] count = new int[26];
        for (int i = left; i <= right; i++) {
            count[s.charAt(i) - 'a']++;
        }
        // 确定分割的字符ch
        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && count[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        // 若所有字符出现次数都大于等于k，则直接返回字符串长度
        if (split == 0) {
            return right - left + 1;
        }
        // 以字符 split 分割字符串，并进行分治递归
        int result = 0;
        while (left <= right) {
            while (left <= right && s.charAt(left) == split) {
                left++;
            }
            if (left > right) {
                break;
            }
            int start = left;
            while (left <= right && s.charAt(left) != split) {
                left++;
            }
            int curLen = dfs(s, start, left - 1, k);
            result = Math.max(result, curLen);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
