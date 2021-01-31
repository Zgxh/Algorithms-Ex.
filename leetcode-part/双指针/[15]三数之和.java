//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 排序 + 双指针。
     * 固定a，双指针法确定 b和 c
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // 先排序：为了利用双指针法
        List<List<Integer>> result = new ArrayList<>();
        for (int a = 0; a < nums.length - 2; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) { // 防止重复
                continue;
            }
            // 对 b,c 的确定采用双指针法
            int c = nums.length - 1;
            for (int b = a + 1; b < c; b++) {
                if (b >= a + 2 && nums[b] == nums[b - 1]) { // 防止重复
                    continue;
                }
                while (b < c && nums[a] + nums[b] > -nums[c]) { // b < c 是必要的
                    c--;
                }
                if (b == c) { // 再次判断while的退出条件，防止b==c但满足下一个if
                    break;
                }
                if (nums[a] + nums[b] + nums[c] == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[a]);
                    temp.add(nums[b]);
                    temp.add(nums[c]);
                    result.add(temp);
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
