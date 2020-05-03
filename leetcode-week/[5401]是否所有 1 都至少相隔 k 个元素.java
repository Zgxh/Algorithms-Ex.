//给你一个由若干 0 和 1 组成的数组 nums 以及整数 k。如果所有 1 都至少相隔 k 个元素，则返回 True ；否则，返回 False 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：nums = [1,0,0,0,1,0,0,1], k = 2
//输出：true
//解释：每个 1 都至少相隔 2 个元素。 
//
// 示例 2： 
//
// 
//
// 输入：nums = [1,0,0,1,0,1], k = 2
//输出：false
//解释：第二个 1 和第三个 1 之间只隔了 1 个元素。 
//
// 示例 3： 
//
// 输入：nums = [1,1,1,1,1], k = 0
//输出：true
// 
//
// 示例 4： 
//
// 输入：nums = [0,1,0,1], k = 1
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10^5 
// 0 <= k <= nums.length 
// nums[i] 的值为 0 或 1 
// 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 1. 简单遍历。
     * 2. 可以用双指针，记录前一个1和后一个1的位置。
     * @param nums
     * @param k
     * @return
     */
    public boolean kLengthApart(int[] nums, int k) {
        int interval = 0;

        // 先找第一个 1 的位置
        int i = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                break;
            }
        }

        // 从第一个 1 之后的第一个数开始遍历
        for (i += 1; i < nums.length; i++) {
            interval++;
            if (nums[i] == 1) {
                if (interval - 1 < k) {
                    return false;
                }
                interval = 0;
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
