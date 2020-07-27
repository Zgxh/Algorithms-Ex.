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
     */

    private int[] nums;
    private boolean[] visited;
    private List<List<Integer>> result = new ArrayList();
    private int len;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }
        this.nums = nums;
        this.len = nums.length;
        this.visited = new boolean[len];
        for (int i = 0; i < len; i++) {
            recursion(new ArrayList(), i, 1);
        }

        return result;
    }

    private void recursion(List<Integer> list, int i, int count) {
        list.add(nums[i]);
        if (count == len) {
            result.add(new ArrayList(list));
            list.remove(count - 1);
            return;
        }
        visited[i] = true;
        for (int j = 0; j < len; j++) {
            if (!visited[j]) {
                recursion(list, j, count + 1);
            }
        }
        visited[i] = false;
        list.remove(count - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
