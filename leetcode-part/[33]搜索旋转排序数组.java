//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
//
// 你可以假设数组中不存在重复的元素。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 示例 1: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
// 
//
// 示例 2: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1 
// Related Topics 数组 二分查找


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 旋转后的数组，二分后，必定有一部分是一直递增的。通过该递增序列来判断下一次二分应该
     * 属于哪一段。
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] nums, int target, int left, int right) {

        if (left == right) {
            return nums[left] == target ? left : -1;
        }

        int mid = (left + right) / 2; // mid会向下取整，如果判断出区间在右边的话，mid应该+1
        if (nums[left] < nums[mid]) {
            if (target > nums[mid] || target < nums[left]) {
                return binarySearch(nums, target, mid + 1, right);
            } else {
                return binarySearch(nums, target, left, mid);
            }
        } else { // 包含left=mid的情况，此时左半区间长度为1，把mid+1变为长度为2
            if (target > nums[right] || target < nums[mid + 1]) {
                return binarySearch(nums, target, left, mid);
            } else {
                return binarySearch(nums, target, mid + 1, right);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
