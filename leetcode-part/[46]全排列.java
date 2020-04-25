//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 回溯法解决全排列问题。
     */

    List<List<Integer>> result = new LinkedList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        for (int num : nums) {
            temp.add(num);
        }
        permuteHelp(nums.length, 0);

        return result;
    }

    private void permuteHelp(int n, int left) {

        if (n == left) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = left; i < n; i++) {
            Collections.swap(temp, left, i); // i 从 left 开始，不交换的也会加进去
            permuteHelp(n, left + 1);
            Collections.swap(temp, left, i);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
