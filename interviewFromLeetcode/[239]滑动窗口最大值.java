//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。 
//
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 进阶： 
//
// 你能在线性时间复杂度内解决此题吗？ 
//
// 
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
// 
// 1 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 1 <= k <= nums.length 
// 
// Related Topics 堆 Sliding Window


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路1：时间复杂度O(n)。利用deque，其中记录元素的index。
     * 思路2：利用maxheap判断每k个数的最大值，然后删窗口的第一个，加入下一个。O(n*logk)
     */

    Deque<Integer> deque = new LinkedList<>(); // 存放index，而且index对应的元素是递减的
    int[] nums;

    public int[] maxSlidingWindow(int[] nums, int k) {

        if (k == 1) {
            return nums;
        }

        this.nums = nums;
        int[] result = new int[nums.length - k + 1];
        int currentMax = 0; // 用于在第一个窗口中确定最大元素

        // 第一个窗口
        for (int i = 0; i < k; i++) {
            cleanDeque(i, i - k);
            deque.addLast(i);

            if (nums[currentMax] < nums[i]) {
                currentMax = i;
            }
        }
        result[0] = nums[currentMax];

        // 剩下的窗口
        for (int i = k; i < nums.length; i++) {
            cleanDeque(i, i - k);
            deque.addLast(i);
            result[i - k + 1] = nums[deque.getFirst()]; // deque中的index对应的元素是递减的，第一个index对应的元素即最大
        }

        return result;
    }

    private void cleanDeque(int current, int remove) {
        if (!deque.isEmpty() && deque.getFirst() == remove) { // 当窗口滑动时，删掉上个窗口的首元素
            deque.removeFirst();
        }
        while (!deque.isEmpty() && nums[deque.getLast()] < nums[current]) { // 来的更大的，deque的last用不上了
            deque.removeLast();
        }
    }


    // public int[] maxSlidingWindow(int[] nums, int k) {
    //
    //     PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    //     int[] result = new int[nums.length - k + 1];
    //
    //     for (int i = 0; i < k; i++) {
    //         maxHeap.add(nums[i]);
    //     }
    //     result[0] = maxHeap.peek();
    //     for (int i = k; i < nums.length; i++) {
    //         maxHeap.remove(nums[i - k]);
    //         maxHeap.add(nums[i]);
    //         result[i - k + 1] = maxHeap.peek();
    //     }
    //
    //     return result;
    // }
}
//leetcode submit region end(Prohibit modification and deletion)
