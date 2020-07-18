//给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第
//一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。 
//
// 示例 1: 
//
// 
//输入: [1,2,1]
//输出: [2,-1,2]
//解释: 第一个 1 的下一个更大的数是 2；
//数字 2 找不到下一个更大的数； 
//第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
// 
//
// 注意: 输入数组的长度不会超过 10000。 
// Related Topics 栈 
// 👍 158 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：递减栈。 单调栈。
     * 需要遍历两遍数组。
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        int len = nums.length;
        int top = 0;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            if (stack.isEmpty()) {
                stack.addFirst(i);
            } else {
                while(!stack.isEmpty() && nums[(top = stack.getFirst())] < nums[i]) {
                    result[top] = nums[i];
                    stack.removeFirst();
                }
                stack.addFirst(i);
            }
        }
        if (!stack.isEmpty()) { // 要再从头来一遍
            int index = stack.getFirst(); // 到最后一个入栈的为止
            for (int i = 0; i < index && !stack.isEmpty(); i++) {
                while(!stack.isEmpty() && nums[(top = stack.getFirst())] < nums[i]) {
                    result[top] = nums[i];
                    stack.removeFirst();
                }
            }
        }
        for (int index : stack) {
            result[index] = -1;
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
