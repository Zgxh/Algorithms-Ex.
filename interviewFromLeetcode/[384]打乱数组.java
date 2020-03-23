//打乱一个没有重复元素的数组。 
//
// 示例: 
//
// 
//// 以数字集合 1, 2 和 3 初始化数组。
//int[] nums = {1,2,3};
//Solution solution = new Solution(nums);
//
//// 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
//solution.shuffle();
//
//// 重设数组到它的初始状态[1,2,3]。
//solution.reset();
//
//// 随机返回数组[1,2,3]打乱后的结果。
//solution.shuffle();
// 
//


import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：Fisher-Yates洗牌算法，在当前位置向右的位置随机抽取与当前位置进行交换。
     *
     */

    int[] nums;
    int[] original;

    public Solution(int[] nums) {
        this.nums = nums;
        original = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        nums = original.clone();
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            swap(i, i + random.nextInt(nums.length - i));
        }
        return nums;
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
//leetcode submit region end(Prohibit modification and deletion)
