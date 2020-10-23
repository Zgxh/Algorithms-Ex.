//字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。 
//
// 
//
// 示例： 
//
// 
//输入：S = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca", "defegde", "hijhklij"。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
// 
//
// 
//
// 提示： 
//
// 
// S的长度在[1, 500]之间。 
// S只包含小写字母 'a' 到 'z' 。 
// 
// Related Topics 贪心算法 双指针 
// 👍 364 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // // 思路1：先得到每个字母的首次和末次出现的位置，然后对区间进行合并
    // public List<Integer> partitionLabels(String S) {
    //     List<Integer> result = new ArrayList();
    //     int len = S.length();
    //     if (len == 0) {
    //         return result;
    //     }
    //     // 寻找每个字符的左右边界
    //     int[][] index = new int[26][2]; // 存放每个字符的第一次出现的位置和最后一次出现的位置
    //     for (int i = 0; i < 26; i++) {
    //         Arrays.fill(index[i], -1);
    //     }
    //     for (int i = 0; i < len; i++) {
    //         int charIndex = S.charAt(i) - 'a';
    //         if (index[charIndex][0] == -1) { // 没遇见过该字符
    //             index[charIndex][0] = index[charIndex][1] = i;
    //         } else { // 更新右边界
    //             index[charIndex][1] = Math.max(index[charIndex][1], i);
    //         }
    //     }
    //     // 对所有的区间按左边界进行排序
    //     Arrays.sort(index, (o1, o2) -> o1[0] - o2[0]);
    //     // 合并重叠的区间
    //     Deque<int[]> stack = new ArrayDeque();
    //     for (int[] charIndex : index) {
    //         if (charIndex[0] == -1) {
    //             continue;
    //         }
    //         if (stack.isEmpty()) {
    //             stack.push(charIndex);
    //             continue;
    //         }
    //         int[] lastIndex = stack.peek();
    //         if (charIndex[0] > lastIndex[1]) {
    //             stack.push(charIndex);
    //             continue;
    //         } else if (charIndex[0] < lastIndex[1] && charIndex[1] > lastIndex[1]) {
    //             // stack.pop();
    //             lastIndex[1] = charIndex[1];
    //         }
    //     }
    //     for (int[] charIndex : stack) {
    //         result.add(charIndex[1] - charIndex[0] + 1);
    //     }
    //     Collections.reverse(result); // 对结果进行反转，因为栈是反的

    //     return result;
    // }

    // 思路2：贪心法。原理是当前片段的结尾比大于等于当前字符的结束位置。
    // 当该片段的结尾与当前字符的结尾位置相遇时，则说明当前片段结束。
    public List<Integer> partitionLabels(String S) {
        // 找到每个字符的最后一次出现的位置
        int[] last = new int[26];
        int length = S.length();
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        // 贪心寻找每个片段的末尾位置
        int start = 0, end = 0; // start 表示该片段的开头，end 表示该片段的结尾
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']); // 当前片段的结尾肯定不会小于当前字符的结尾
            // 当字符的结尾与片段的结尾撞上了，则当前片段结束，开始新的片段
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1; // start 更新为新的片段
            }
        }

        return partition;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
