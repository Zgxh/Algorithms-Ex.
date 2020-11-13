//给定一个整数数组 nums ，你可以对它进行一些操作。 
//
// 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] +
// 1 的元素。 
//
// 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。 
//
// 示例 1: 
//
// 
//输入: nums = [3, 4, 2]
//输出: 6
//解释: 
//删除 4 来获得 4 个点数，因此 3 也被删除。
//之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
// 
//
// 示例 2: 
//
// 
//输入: nums = [2, 2, 3, 3, 3, 4]
//输出: 9
//解释: 
//删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
//之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
//总共获得 9 个点数。
// 
//
// 注意: 
//
// 
// nums的长度最大为20000。 
// 每个整数nums[i]的大小都在[1, 10000]范围内。 
// 
// Related Topics 动态规划 
// 👍 199 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 此问题等价于打家劫舍问题，先把所有数字出现的次数统计出来，按数字从小到大排列；
    // 然后相邻的两个数字（相邻是指数值相邻，即2和3这种）不能同时获得
    public int deleteAndEarn(int[] nums) {
        int[] count = new int[10001]; // 统计每个数字出现的次数
        for (int num : nums) {
            count[num]++;
        }
        // DP 计算：如果给出前i个数字，则最大可以获得多少点数
        int first = 0, second = 0;
        int secondIndex = 0;
        for (int i = 1; i < 10001; i++) {
            if (count[i] == 0) { // 只统计有数字存在的位置
                continue;
            }
            int record = second;
            // DP 更新的时候，若当前位置i与之前计算过的index相邻时，不能同时获得
            if (i == secondIndex + 1) {
                int temp = first + i * count[i];
                if (second < temp) {
                    second = temp;
                    secondIndex = i;
                }
            } else { // 如果不相邻的话，直接获得
                second += i * count[i];
                secondIndex = i;
            }
            first = record;
        }

        return second;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
