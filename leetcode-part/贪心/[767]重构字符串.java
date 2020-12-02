//给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。 
//
// 若可行，输出任意可行的结果。若不可行，返回空字符串。 
//
// 示例 1: 
//
// 
//输入: S = "aab"
//输出: "aba"
// 
//
// 示例 2: 
//
// 
//输入: S = "aaab"
//输出: ""
// 
//
// 注意: 
//
// 
// S 只包含小写字母并且长度在[1, 500]区间内。 
// 
// Related Topics 堆 贪心算法 排序 字符串 
// 👍 252 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // // 基于贪心思想的两种策略：

    // // 方法1：基于大顶堆，每次取两个出现次数最多的字符来排放在一块
    // // 时间复杂度：O(n log 26), 空间复杂度：O(26)
    // public String reorganizeString(String S) {
    //     int len = S.length();
    //     if (len < 2) {
    //         return S;
    //     }
    //     // 统计每个字符的出现次数
    //     int[] count = new int[26];
    //     for (char ch : S.toCharArray()) {
    //         count[ch - 'a'] += 1;
    //     }
    //     // 建立大顶堆，对字符按出现次数进行排序
    //     PriorityQueue<Character> maxHeap = new PriorityQueue<Character>((ch1, ch2) -> count[ch2 - 'a'] - count[ch1 - 'a']);
    //     final int MAX = (len + 1) / 2;
    //     for (int i = 0; i < 26; i++) {
    //         if (count[i] > MAX) { // 如果单个字符的出现次数太多，则不可能成功重排
    //             return "";
    //         }
    //         // 把出现过的字符加入到大顶堆中
    //         if (count[i] > 0) {
    //             maxHeap.offer((char)('a' + i));
    //         }
    //     }
    //     StringBuilder result = new StringBuilder();
    //     // 每次弹出两个出现次数最多的字符，加入到结果中，然后count减少，并重新入堆，反复判断
    //     while (maxHeap.size() > 1) {
    //         char ch1 = maxHeap.poll();
    //         char ch2 = maxHeap.poll();
    //         result.append(ch1);
    //         result.append(ch2);
    //         count[ch1 - 'a']--;
    //         count[ch2 - 'a']--;
    //         if (count[ch1 - 'a'] > 0) {
    //             maxHeap.offer(ch1);
    //         }
    //         if (count[ch2 - 'a'] > 0) {
    //             maxHeap.offer(ch2);
    //         }
    //     }
    //     // 如果字符个数是奇数个，则还有一个剩余的字符
    //     if (!maxHeap.isEmpty()) {
    //         result.append(maxHeap.poll());
    //     }

    //     return result.toString();
    // }

    // 方法2：分奇偶位置，每次排完一个字符的所有出现次数
    // 时间复杂度：O(n)，空间复杂度：O(26)
    public String reorganizeString(String S) {
        int len = S.length();
        if (len < 2) {
            return S;
        }
        // 统计字符的出现次数
        int[] count = new int[26];
        final int MAX = (len + 1) / 2;
        for (char ch : S.toCharArray()) {
            count[ch - 'a'] += 1;
            if (count[ch - 'a'] > MAX) {
                return "";
            }
        }
        char[] result = new char[len];
        // 按奇偶来排布字符
        int evenIndex = 0, oddIndex = 1;
        final int ODD_MAX = len / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            // 优先排到奇数位置上，前提是出现次数不能到一半
            // 例如：len=3，但有2个1，因此2个1必须都排在index为偶数的位置上
            while (count[i] > 0 && count[i] <= ODD_MAX && oddIndex < len) {
                result[oddIndex] = c;
                count[i]--;
                oddIndex += 2;
            }
            // 如果把奇数位置排到头了，但当前字符还有剩余的没排上，则排到偶数位置上
            while (count[i] > 0) {
                result[evenIndex] = c;
                count[i]--;
                evenIndex += 2;
            }
        }

        return new String(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
