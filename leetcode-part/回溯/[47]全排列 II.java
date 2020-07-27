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
     * 是46题全排列的升级版，存在重复的值，需要判断一下当前位置的值在上一次回溯中
     * 有没有已经被使用过了。
     */

    private int[] nums;
    private boolean[] visited;
    private int len;
    private List<List<Integer>> result = new ArrayList();

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.len = nums.length;
        if (len == 0) {
            return new ArrayList();
        }
        Arrays.sort(nums);
        this.nums = nums;
        this.visited = new boolean[len];
        recursion(new ArrayList(), 0, 1);
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[i- 1]) {
                recursion(new ArrayList(), i, 1);
            }
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
        int lastIndex = -1; // 记录上一次回溯使用的值
        for (int j = 0; j < len; j++) {
            if (j == 0 && !visited[0]) {
                recursion(list, j, count + 1);
                lastIndex = 0;
            } else {
                if (!visited[j] && (lastIndex == -1 || nums[j] != nums[lastIndex])) {
                    lastIndex = j;
                    recursion(list, j, count + 1);
                }
            }
        }
        list.remove(count - 1);
        visited[i] = false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
