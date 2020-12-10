//给你一个非空的字符串 s 和一个整数 k，你要将这个字符串中的字母进行重新排列，使得重排后的字符串中相同字母的位置间隔距离至少为 k。 
//
// 所有输入的字符串都由小写字母组成，如果找不到距离至少为 k 的重排结果，请返回一个空字符串 ""。 
//
// 示例 1： 
//
// 输入: s = "aabbcc", k = 3
//输出: "abcabc" 
//解释: 相同的字母在新的字符串中间隔至少 3 个单位距离。
// 
//
// 示例 2: 
//
// 输入: s = "aaabc", k = 3
//输出: "" 
//解释: 没有办法找到可能的重排结果。
// 
//
// 示例 3: 
//
// 输入: s = "aaadbbcc", k = 2
//输出: "abacabcd"
//解释: 相同的字母在新的字符串中间隔至少 2 个单位距离。
// 
// Related Topics 堆 贪心算法 哈希表 
// 👍 49 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 堆的解法
    public String rearrangeString(String s, int k) {
        if (k == 0) {
            return s;
        }
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        // 大顶堆，排序规则：按字符的出现次数，如果次数相等，则字典序小的更大
        PriorityQueue<Character> maxHeap = new PriorityQueue<Character>((o1, o2) -> {
            if (count[o1 - 'a'] != count[o2 - 'a']) {
                return count[o2 - 'a'] - count[o1 - 'a'];
            } else {
                return o1 - o2;
            }
        });
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                maxHeap.offer((char) (i + 'a'));
            }
        }
        StringBuilder result = new StringBuilder();
        // 当堆中元素数目大于等于 k 时，每次拿出k个，接在 result 上
        while (maxHeap.size() >= k) {
            List<Character> list = new ArrayList(k); // 临时保存拿出来的 k 个字符
            for (int i = 0; i < k; i++) {
                Character ch = maxHeap.poll();
                count[ch - 'a']--;
                result.append(ch);
                list.add(ch);
            }
            // 如果拿出来的字符还有次数，则再次放入堆中
            for (Character ch : list) {
                if (count[ch - 'a'] > 0) {
                    maxHeap.offer(ch);
                }
            }
        }
        // 不足 k 个字符的部分，如果都只剩一个，则直接添加到结果中即可
        // 如果次数大于 1，则不能成功放置，返回空字符串
        while (!maxHeap.isEmpty()) {
            Character ch = maxHeap.poll();
            if (count[ch - 'a'] > 1) {
                return "";
            }
            result.append(ch);
        }

        return result.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
