//给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。 
//
// 示例 1: 
//
// 
//输入：
//nums = [1,3,1]
//k = 1
//输出：0 
//解释：
//所有数对如下：
//(1,3) -> 2
//(1,1) -> 0
//(3,1) -> 2
//因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
// 
//
// 提示: 
//
// 
// 2 <= len(nums) <= 10000. 
// 0 <= nums[i] < 1000000. 
// 1 <= k <= len(nums) * (len(nums) - 1) / 2. 
// 
// Related Topics 堆 数组 二分查找 
// 👍 135 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 巨 TM 难
    // 二分查找 + 双指针
    // 二分是因为任意数对之间的差值，在排序后，一定大于等于 0，小于等于 nums[len - 1] - nums[0]
    // 二分用来查找一个合适的数对差 mid，使小于 mid 的数对个数正好为 k。
    // 通过双指针来计算 nums[] 中所有 <= mid 的数对个数 count，并依据 count 来确定下一步的二分更新方向
    // 时间复杂度：O(n log n + n log M): n 是数组长度，M 是最大数对差
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0, right = nums[len - 1] - nums[0];
        // 找到 >= k 的 mid
        while (left < right) {
            int mid = left + (right - left) / 2;
            int low = 0, count = 0;
            // 对于每个 high，找到对应的 low，使[low, high] 之间的所有数对的差 <= mid
            for (int high = 0; high < len; high++) {
                while (nums[high] - nums[low] > mid) {
                    low++;
                }
                count += high - low;
            }
            // 最终找到的 left = right = mid 一定是对应
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
