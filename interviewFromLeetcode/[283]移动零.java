//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针




//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 思路：双指针，i代表慢指针，j是快指针。每次j找到一个非零元素，
     * 就利用i依次排在数组nums[]前面，最后把剩下的位置置零。
     * 时间复杂度：O(n)
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[i++] = num;
            }
        }
        while (i < nums.length) {
            nums[i++] = 0;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
