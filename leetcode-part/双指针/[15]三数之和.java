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
     * 思路：双指针。
     * 固定a，双指针为b和c，控制c的大小来移动右指针。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // 先排序，防止重复结果
        List<List<Integer>> result = new ArrayList<>();
        for (int a = 0; a < nums.length - 2; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) { // 防止重复
                continue;
            }
            int c = nums.length - 1;
            for (int b = a + 1; b < c; b++) {
                if (b >= a + 2 && nums[b] == nums[b - 1]) { // 防止重复
                    continue;
                }
                while (c > b && nums[a] + nums[b] > -nums[c]) {
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
