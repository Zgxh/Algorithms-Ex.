//给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。 
//
// 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i, j < n 。 
//
// 你能在O(n)的时间解决这个问题吗？ 
//
// 示例: 
//
// 
//输入: [3, 10, 5, 25, 2, 8]
//
//输出: 28
//
//解释: 最大的结果是 5 ^ 25 = 28.
// 
// Related Topics 位运算 字典树 
// 👍 158 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路1:哈希表存放前缀
     * 从最高位开始，每次把前i位加入哈希表中，利用哈希表的查询O(1)的特性，
     * 判断是否存在两个数使前i位二进制中前缀的异或值最大，并把上一轮的结果
     * 保持到下一轮。
     */
    public int findMaximumXOR(int[] nums) {
        // 首先求出所有数中最长的二进制串的长度
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        int len = Integer.toBinaryString(maxNum).length();
        int maxXOR = 0, curXOR = 0; // 记录当前最大的异或值，和当前要判断的异或值
        for (int i = len - 1; i >= 0; i--) { // 从最高位开始逐层判断
            maxXOR <<= 1; // 左移判断下一位
            curXOR = maxXOR + 1; // +1 表示当前要判断的这一位
            Set<Integer> prefixes = new HashSet();
            for (int num : nums) {
                prefixes.add(num >> i);
            }
            for (int prefix : prefixes) { // A ^ B = curXOR
                if (prefixes.contains(prefix ^ curXOR)) {
                    maxXOR = curXOR; // 更新当前最大异或值，并保持到下一轮
                    break;
                }
            }
        }

        return maxXOR;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
