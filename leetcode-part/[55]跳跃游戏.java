//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 贪心策略。
     * 维护一个数字currentMax表示该位置之前的元素能到达的最大位置，
     * 如果currentMax到不了当前位置，则肯定到不了最后。
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        int currentMax = 0; // 已知能到达的最大位置

        for (int i = 0; i < nums.length; i++) {
            if (currentMax < i) { // i位置之前的元素到不了i位置，自然无法继续往下走
                return false;
            }
            currentMax = Math.max(currentMax, i + nums[i]);
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
