//给定一个未排序的整数数组，找出最长连续序列的长度。 
//
// 要求算法的时间复杂度为 O(n)。 
//
// 示例: 
//
// 输入: [100, 4, 200, 1, 3, 2]
//输出: 4
//解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。 
// Related Topics 并查集 数组


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 哈希。
     * 利用哈希set的查询为O(1)与去重的特性。只对最小的连续序列进行扫描计算，保证O(n).
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int maxLength = 0;
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet()); // 默认是哈希set
        for (int num : set) {
            if (!set.contains(num - 1)) { // 不重复计算同样的连续序列
                int curLength = 1;
                while (set.contains(num + 1)) {
                    num++;
                    curLength++;
                }
                maxLength = Math.max(maxLength, curLength);
            }
        }

        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
