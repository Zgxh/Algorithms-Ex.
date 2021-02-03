//升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
// 
//
// 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// nums 肯定会在某个点上旋转 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 二分查找 
// 👍 1165 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 二分：先找单调的区间，然后根据单调区间确定边界更新方向
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        // 二分，先找出单调的区间，然后利用单调性调整二分左右边界
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) { // 如果 [left, mid] 是单调的
                if (nums[left] <= target && nums[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else { // 如果 [mid + 1, right] 是单调的
                if (nums[mid + 1] <= target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        return nums[left] == target ? left : -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
