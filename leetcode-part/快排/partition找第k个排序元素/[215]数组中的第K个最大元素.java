//在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 示例 1: 
//
// 输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 说明: 
//
// 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。 
// Related Topics 堆 分治算法 
// 👍 872 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // partition
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int index = partition(nums, left, right);
            if (index == len - k) {
                left = index;
                break;
            } else if (index < len - k) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }

        return nums[left];
    }

    private int partition(int[] nums, int left, int right) {
        int target = nums[right];
        while (left < right) {
            while (left < right && nums[left] < target) {
                left++;
            }
            nums[right] = nums[left];
            while (left < right && nums[right] >= target) {
                right--;
            }
            nums[left] = nums[right];
        }
        nums[left] = target;

        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
