//给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都
//可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。 
//
// 示例 1: 
//
// 输入: nums = [1,3], n = 6
//输出: 1 
//解释:
//根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
//现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
//其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
//所以我们最少需要添加一个数字。 
//
// 示例 2: 
//
// 输入: nums = [1,5,10], n = 20
//输出: 2
//解释: 我们需要添加 [2, 4]。
// 
//
// 示例 3: 
//
// 输入: nums = [1,2,2], n = 5
//输出: 0
// 
// Related Topics 贪心算法 
// 👍 182 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 贪心算法：原则：如果 1~x-1 内能组合，则若 x 存在的话，1~2x-1 内也能组合，因为只需加上 x 即可。
    public int minPatches(int[] nums, int n) {
        int result = 0;
        int len = nums.length;
        int index = 0;
        long x = 1;
        while (x <= n) {
            // 假设上一轮的 x 为 y，则小于 2y 的都可以到达
            // 这一轮的 x 就是 2y
            if (index < len && nums[index] <= x) {
                x += nums[index];
                index++;
            } else { // 这一轮完事，下一轮：2x
                x *= 2; //
                result++; // 补充上 x
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
