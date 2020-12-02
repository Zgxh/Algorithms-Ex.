//给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接
//成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。 
//
// 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。 
//
// 说明: 请尽可能地优化你算法的时间和空间复杂度。 
//
// 示例 1: 
//
// 输入:
//nums1 = [3, 4, 6, 5]
//nums2 = [9, 1, 2, 5, 8, 3]
//k = 5
//输出:
//[9, 8, 6, 5, 3] 
//
// 示例 2: 
//
// 输入:
//nums1 = [6, 7]
//nums2 = [6, 0, 4]
//k = 5
//输出:
//[6, 7, 6, 0, 4] 
//
// 示例 3: 
//
// 输入:
//nums1 = [3, 9]
//nums2 = [8, 9]
//k = 3
//输出:
//[9, 8, 9] 
// Related Topics 贪心算法 动态规划 
// 👍 274 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    /**
     * 单调栈 + 归并
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] result = new int[k];
        // 把 k 分到两个数组上，遍历所有的组合，寻找最大的子序列
        for (int i = 0; i <= k; i++) {
            if (i > nums1.length || (k - i) > nums2.length) {
                continue;
            }
            int[] seq1 = maxSubsequence(nums1, i);
            int[] seq2 = maxSubsequence(nums2, k - i);
            int[] merged = merge(seq1, seq2);
            if (moreThan(merged, result, 0, 0)) {
                result = merged;
            }
        }

        return result;
    }

    // 利用单调栈来计算 nums 数组中选 k 个元素使所得的序列最大
    private int[] maxSubsequence(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] stack = new int[k];
        int top = -1; // 栈顶指针
        // len 个元素中，选 k 个，每跳过一个元素不选后，剩余可跳过的元素个数为 remain
        int remain = len - k; // 剩余可跳过的元素个数
        for (int cur : nums) {
            while (top >= 0 && stack[top] < cur && remain > 0) {
                top--;
                remain--; // 代表跳过之前加入到 stack 中的元素
            }
            if (top < k - 1) {
                stack[++top] = cur;
            } else { // 当前元素比栈顶元素小，且可以跳过，则跳过
                remain--; // 代表跳过当前元素
            }
        }

        return stack;
    }

    // 合并两个数组
    // 重点在于碰到两个元素相等的时候，需要继续往下判断，即判断由当前位置引导的 seq1
    // 和 seq2 的子串谁更大，这时可以复用 moreThan() 函数
    private int[] merge(int[] seq1, int[] seq2) {
        int len1 = seq1.length, len2 = seq2.length;
        int pointer1 = 0, pointer2 = 0, index = 0;
        int[] result = new int[len1 + len2];
        while (pointer1 < len1 && pointer2 < len2) {
            if (seq1[pointer1] > seq2[pointer2]) {
                result[index++] = seq1[pointer1++];
            } else if (seq1[pointer1] < seq2[pointer2]) {
                result[index++] = seq2[pointer2++];
            } else { // 如果当前字母相同，需要向后继续判断，直到一个到头了，或者碰到不相等的字母
                if (moreThan(seq1, seq2, pointer1, pointer2)) {
                    result[index++] = seq1[pointer1++];
                } else {
                    result[index++] = seq2[pointer2++];
                }
            }
        }
        while (pointer1 < len1) {
            result[index++] = seq1[pointer1++];
        }
        while (pointer2 < len2) {
            result[index++] = seq2[pointer2++];
        }

        return result;
    }

    // 判断 seq1 是否比 seq2 序列更大
    private boolean moreThan(int[] seq1, int[] seq2, int index1, int index2) {
        int len1 = seq1.length, len2 = seq2.length;
        if (index1 == len1) {
            return false;
        } else if (index2 == len2) {
            return true;
        }
        while (index1 < len1 && index2 < len2) {
            if (seq1[index1] < seq2[index2]) {
                return false;
            } else if (seq1[index1] > seq2[index2]) {
                return true;
            } else {
                index1++;
                index2++;
            }
        }
        if (index1 == len1) {
            return false;
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
