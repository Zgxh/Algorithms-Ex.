//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：双指针，与15题[三数之和]类似。
     * 先固定a，然后b和c双指针夹逼。
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int result = nums[0] + nums[1] + nums[2];

        // i, j, k 分别代表 nums[]中的 a, b, c 的 index
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { // 避免 a 重复
                continue;
            }
            int j = i + 1, k = len - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) { // 直接遇到最优答案
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) { // 更新答案
                    result = sum;
                }
                if (sum < target) {
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) { // 避免 b 重复
                        j++;
                    }
                } else {
                    k--;
                    while (j < k && nums[k] == nums[k + 1]) { // 避免 c 重复
                        k--;
                    }
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
