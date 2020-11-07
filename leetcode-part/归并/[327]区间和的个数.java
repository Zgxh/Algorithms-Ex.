//给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。 
//区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。 
//
// 说明: 
//最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。 
//
// 示例: 
//
// 输入: nums = [-2,5,-1], lower = -2, upper = 2,
//输出: 3 
//解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 221 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * O(N log N) 归并排序法。
     * 具体思路看题解 1.
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        long[] sum = new long[len + 1]; // 前缀和
        sum[0] = 0;
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        return countRangeSumRecursive(sum, lower, upper, 0, len);
    }

    public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = left + (right - left) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int result = n1 + n2;

            // 统计位于 lower 与 upper 之间的下标对的数量
            // i 为左半区间的指针，遍历归并的左半区间每个位置
            int i = left;
            // l 和 r 为右半区间的指针，寻找位于 lower 和 upper 之间的序列
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) { // 找到第一个大于 lower 的 l 位置
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) { // 找到第一个小于 upper 的 r 位置
                    r++;
                }
                result += r - l; // 包括 l，但不包括 r
                i++;
            }

            // 合并两个排序数组，即归并排序的 merge() 操作
            int[] sorted = new int[right - left + 1]; // 临时数组来辅助归并
            int p1 = left, p2 = mid + 1, p = 0;
            while (p1 <= mid && p2 <= right) {
                if (sum[p1] < sum[p2]) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    sorted[p++] = (int) sum[p2++];
                }
            }
            while (p1 <= mid) {
                sorted[p++] = (int) sum[p1++];
            }
            while (p2 <= right) {
                sorted[p++] = (int) sum[p2++];
            }
            for (int j = 0; j < sorted.length; j++) { // 写回原数组
                sum[left + j] = sorted[j];
            }

            return result;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
