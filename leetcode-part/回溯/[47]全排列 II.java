//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法 
// 👍 359 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：回溯
     *
     * 是46题全排列的升级版，同一个位置应该优先考虑重复值上比较靠前的数字。
     */

    private List<List<Integer>> result;
    private int[] nums;
    private int len;
    private boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.result = new ArrayList();
        if (nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        this.nums = nums;
        this.len = nums.length;
        this.visited = new boolean[len];
        backtracking(new ArrayList(), 0);

        return result;
    }

    private void backtracking(List<Integer> list, int index) {
        if (index == len) {
            result.add(new ArrayList(list));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            backtracking(list, index + 1);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
