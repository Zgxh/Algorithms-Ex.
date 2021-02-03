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
// 👍 806 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：经典的回溯题目。
     *
     * 时间复杂度：O(n*n!)：一共 n! 种排列，每种排列内部有 n 个结点
     */

    private List<List<Integer>> result;
    private int[] nums;
    private int len;
    private boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        this.result = new ArrayList();
        if (nums == null || nums.length == 0) {
            return result;
        }
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
            if (visited[i]) {
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
