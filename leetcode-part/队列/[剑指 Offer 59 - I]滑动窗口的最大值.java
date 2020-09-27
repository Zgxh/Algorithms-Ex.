//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/ 
// Related Topics 队列 Sliding Window 
// 👍 115 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 单调队列，非递增序
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> decQueue = new LinkedList();
        int left = 0, right = 0;
        // 用前k个数字来初始化单调队列
        while (right < k) {
            while (!decQueue.isEmpty() && nums[right] > decQueue.getLast()) {
                decQueue.pollLast();
            }
            decQueue.offer(nums[right]);
            right++;
        }
        int len = nums.length;
        int[] max = new int[len - k + 1];
        int index = 0;
        max[index++] = decQueue.peek();
        // 窗口右移，更新单调队列，并得到max值
        while (right < len) {
            if (decQueue.peek() == nums[left]) {
                decQueue.poll();
            }
            left++;
            while (!decQueue.isEmpty() && nums[right] > decQueue.getLast()) {
                decQueue.pollLast();
            }
            decQueue.offer(nums[right]);
            right++;
            max[index++] = decQueue.peek();
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
