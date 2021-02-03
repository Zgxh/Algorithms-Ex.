//中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。 
//
// 例如： 
//
// 
// [2,3,4]，中位数是 3 
// [2,3]，中位数是 (2 + 3) / 2 = 2.5 
// 
//
// 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗
//口中元素的中位数，并输出由它们组成的数组。 
//
// 
//
// 示例： 
//
// 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。 
//
// 
//窗口位置                      中位数
//---------------               -----
//[1  3  -1] -3  5  3  6  7       1
// 1 [3  -1  -3] 5  3  6  7      -1
// 1  3 [-1  -3  5] 3  6  7      -1
// 1  3  -1 [-3  5  3] 6  7       3
// 1  3  -1  -3 [5  3  6] 7       5
// 1  3  -1  -3  5 [3  6  7]      6
// 
//
// 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。 
//
// 
//
// 提示： 
//
// 
// 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。 
// 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。 
// 
// Related Topics Sliding Window 
// 👍 184 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 大顶堆 + 小顶堆：实时维护中位数
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] result = new double[len - k + 1];
        int more = k % 2 == 1 ? 1 : 0; // 大顶堆始终比小顶堆中多more个数字，more根据k的奇偶确定
        boolean odd = k % 2 == 1;
        // 大顶堆中存放的是k个数中较小的一半，小顶堆中存放的是较大的一半
        // 注意这里的比较器不能直接 o2-o1，因为可能数字过大造成越界从而符号错误
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((o1, o2) -> {
            if (o1 < o2) {
                return -1;
            } else {
                return 1;
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((o1, o2) -> {
            if (o1 < o2) {
                return 1;
            } else {
                return -1;
            }
        });
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }
        // 保证大顶堆的大小始终比小顶堆多more个
        while (maxHeap.size() - minHeap.size() > more) {
            minHeap.offer(maxHeap.poll());
        }
        result[0] = odd ? maxHeap.peek() : ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
        int left = 0, right = k;
        while (right < len) {
            // 尝试从两个堆中删除：如果大顶堆中没有，则一定在小顶堆中
            if (!maxHeap.remove(nums[left])) {
                minHeap.remove(nums[left]);
            }
            left++;
            // 如果是从大顶堆中删除的，则先向小顶堆中借一个，因为新进来的数字可能比这个借的数字还要大
            if (maxHeap.size() - minHeap.size() < more && !minHeap.isEmpty()) {
                maxHeap.offer(minHeap.poll());
            }
            maxHeap.offer(nums[right]);
            right++;
            // 保证大顶堆的大小始终比小顶堆多more个
            if (maxHeap.size() - minHeap.size() > more) {
                minHeap.offer(maxHeap.poll());
            }
            result[left] = odd ? maxHeap.peek() : ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
