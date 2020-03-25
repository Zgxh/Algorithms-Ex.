//给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之
//外其余各元素的乘积。 
//
// 
//
// 示例: 
//
// 输入: [1,2,3,4]
//输出: [24,12,8,6] 
//
// 
//
// 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。 
//
// 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。 
//
// 进阶： 
//你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。） 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 除了result数组外，使用常数空间，则分成两次遍历。因为每个位置都是左边乘积 乘上 右边乘积，
     * 第一次遍历算左侧乘积，第二次乘上右侧乘积。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {

        int[] result = new int[nums.length];
        int curProduct = 1;

        for (int i = 1; i < nums.length; i++) {
            curProduct = nums[i - 1] * curProduct;
            result[i] = curProduct;
        }
        curProduct = 1;
        result[0] = 1; // 后面用到result[0]作为乘子了，先初始化为 1
        for (int i = nums.length - 2; i >= 0; i--) {
            curProduct = nums[i + 1] * curProduct;
            result[i] *= curProduct;
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
