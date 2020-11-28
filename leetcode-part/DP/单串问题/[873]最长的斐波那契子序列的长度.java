//如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的： 
//
// 
// n >= 3 
// 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2} 
// 
//
// 给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回 0 。 
//
// （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3
//, 4, 5, 6, 7, 8] 的一个子序列） 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入: [1,2,3,4,5,6,7,8]
//输出: 5
//解释:
//最长的斐波那契式子序列为：[1,2,3,5,8] 。
// 
//
// 示例 2： 
//
// 输入: [1,3,7,11,12,14,18]
//输出: 3
//解释:
//最长的斐波那契式子序列有：
//[1,11,12]，[3,11,14] 以及 [7,11,18] 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= A.length <= 1000 
// 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9 
// （对于以 Java，C，C++，以及 C# 的提交，时间限制被减少了 50%） 
// 
// Related Topics 数组 动态规划 
// 👍 133 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // DP 问题。
    // 使用 hashmap 来存放 DP 数组。
    public int lenLongestFibSubseq(int[] A) {
        int len = A.length;
        if (len == 0) {
            return 0;
        }
        Map<Integer, Integer> index = new HashMap(); // 保存A[]中数字的index
        for (int i = 0; i < len; i++) {
            index.put(A[i], i);
        }
        Map<String, Integer> dp = new HashMap(); // dp.get(string): string 对应序列的最后两个元素的组合哈希值，对应的斐波那契子序列的长度
        int maxLen = 0;
        // 以 A[i] 为当前元素，寻找满足 A[i] = A[j] + A[k] 的元素，j为区间左端点index，i为区间右端点index
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] - A[j] >= A[j]) { // 保证 k < j， 为了避免重复计算，因为 A[] 是单调增的，所以可以这么判断
                    continue;
                }
                Integer k = index.get(A[i] - A[j]); // 寻找 A[k] 对应的位置
                if (k != null) { // 如果 A[k] 存在
                    int length = dp.getOrDefault(k + "#" + j, 2) + 1;
                    dp.put(j + "#" + i, length); // 储存本序列的最后两个元素对应的子序列的最大长度
                    maxLen = Math.max(maxLen, length);
                }
            }
        }

        return maxLen >= 3 ? maxLen : 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
