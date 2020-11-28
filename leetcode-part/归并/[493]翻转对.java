//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 164 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 借助归并排序来找逆序对
    public int reversePairs(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        return reversePairsRecursive(nums, 0, nums.length - 1);
    }

    public int reversePairsRecursive(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = left + (right - left) / 2;
            int result1 = reversePairsRecursive(nums, left, mid);
            int result2 = reversePairsRecursive(nums, mid + 1, right);
            int result = result1 + result2;
            // 统计下标对
            int i = left;
            int j = mid + 1;
            // 左右区间分别都是排好序的，线性O(n)计算所有的重要反转对数目
            while (i <= mid) { // 对于每个i，找到第一个不满足条件的位置
                while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                    j++;
                }
                result += j - mid - 1; // (j-1) - (mid+1) + 1
                i++;
            }
            // merge
            int[] sorted = new int[right - left + 1]; // 辅助数组
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid && p2 <= right) {
                if (nums[p1] < nums[p2]) {
                    sorted[p++] = nums[p1++];
                } else {
                    sorted[p++] = nums[p2++];
                }
            }
            while (p1 <= mid) {
                sorted[p++] = nums[p1++];
            }
            while (p2 <= right) {
                sorted[p++] = nums[p2++];
            }
            // 复制回原数组
            for (int k = 0; k < sorted.length; k++) {
                nums[left + k] = sorted[k];
            }

            return result;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
