//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路1：排序，中间位置的数即为所求。时间O(nlogn)，空间O(logn)
     * 思路2：摩尔投票法。把众数记为1，非众数记为-1，则所有数加起来肯定大于0.
     * 遇到相同的数票数+1.遇到不同的数票数-1。当票为0时，换下个位置的数。
     * 最后那个数的票数肯定大于0，结果就是最后那个数。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        int currentNum = nums[0];
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                currentNum = num;
            }
            count += (currentNum == num) ? 1 : -1;
        }

        return currentNum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
