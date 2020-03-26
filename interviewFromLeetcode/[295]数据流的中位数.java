//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
// Related Topics 堆 设计


import java.util.Collections;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {

    /**
     * 思路： 同时维护大顶堆与小顶堆，其中大顶堆中存放数据流较小的数，小顶堆存放数据流中较大的数。
     * 每次新来元素先进入大顶堆筛选，然后把堆顶给小顶堆筛选，这个过程保证了大顶堆元素始终小于小顶堆。
     * 与此同时，保证两个堆的数据容量平衡，小顶堆的size始终 ≥ 大顶堆的size，且差值不大于 1，这样中位数即可直接获取。
     */

    /** initialize your data structure here. */
    public PriorityQueue<Integer> maxheap, minheap;
    public MedianFinder() {
        minheap = new PriorityQueue<Integer>();
        maxheap = new PriorityQueue<Integer>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        /*先在大顶堆中排序，找到最大的给小顶堆；再在小顶堆中排序，找到最小的；保证最小堆的最小值大于最大堆的最大值 */
        maxheap.add(num);
        minheap.add(maxheap.poll());
        if (minheap.size() > maxheap.size()) {
            maxheap.add(minheap.poll());
        }
    }

    public double findMedian() {
        if (maxheap.size() == minheap.size()) {
            return (maxheap.element() + minheap.element()) * 0.5;
        } else {
            return maxheap.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
