//在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 示例 1: 
//
// 输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 说明: 
//
// 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。 
// Related Topics 堆 分治算法


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 利用小顶堆，每当堆中元素大于k个就把堆顶弹出，当堆中只剩k个元素时，堆顶就是第K大的元素。
     *
     * 也可以利用大顶堆，把所有元素都进堆，然后依次弹出k-1个堆顶，下一个堆顶就是第K大的元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
