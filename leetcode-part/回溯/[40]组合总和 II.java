//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法 
// 👍 360 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 回溯。
     */

    private int[] candidates;
    private List<List<Integer>> result;
    private int len;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.len = candidates.length;
        this.result = new ArrayList();
        Arrays.sort(candidates);
        this.candidates = candidates;
        backtracking(new ArrayList(), 0, target, -1);

        return result;
    }

    private void backtracking(List<Integer> list, int index, int target, int lastIndex) {
        if (target == 0) {
            result.add(new ArrayList(list));
            return;
        }
        if (index == len || target < candidates[index]) {
            return;
        }
        // 加入当前元素
        if (!(lastIndex + 1 < index && candidates[index] == candidates[index - 1])) { // 避免重复的情况
            list.add(candidates[index]);
            backtracking(list, index + 1, target - candidates[index], index);
            list.remove(list.size() - 1);
        }

        // 不加入当前元素
        backtracking(list, index + 1, target, lastIndex);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
