//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 要求只用O(1)的额外空间。
     * 每次把当前位置元素移动k个位置，并用temp记录下还没被安放的元素，每次循环停止的条件是遇到了开始位置。
     * 用count对当前已移动过的元素进行计数，总数达到了nums.length即停止。
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {

        k = k % nums.length;

        for (int i = 0, count = 0; count < nums.length; i++) {
            int current = i;
            int last = nums[current];

            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = last;
                current = next;
                last = temp;
                count++;
            } while (i != current);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
