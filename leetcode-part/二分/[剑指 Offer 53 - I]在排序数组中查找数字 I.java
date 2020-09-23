//统计一个数字在排序数组中出现的次数。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [5,7,7,8,8,10], target = 8
//输出: 2 
//
// 示例 2: 
//
// 输入: nums = [5,7,7,8,8,10], target = 6
//输出: 0 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
//
// 
//
// 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
//position-of-element-in-sorted-array/ 
// Related Topics 数组 二分查找 
// 👍 65 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 二分，分别查找左边界和右边界
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int right = binaryRightBoundary(nums, target);
        if (right == -1) {
            return 0;
        }
        int left = binaryLeftBoundary(nums, target);

        return right - left + 1;
    }

    private int binaryLeftBoundary(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left] == target ? left : -1;
    }

    private int binaryRightBoundary(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid + 1] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left] == target ? left : -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
