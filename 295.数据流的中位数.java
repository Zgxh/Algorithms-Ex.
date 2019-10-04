import java.util.Collections;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=295 lang=java
 *
 * [295] 数据流的中位数
 */
class MedianFinder {

    /** initialize your data structure here. */
    public PriorityQueue<Integer> maxheap, minheap;
    public MedianFinder() {
        minheap = new PriorityQueue<Integer>(); //小顶堆存放最大堆
        maxheap = new PriorityQueue<Integer>(Collections.reverseOrder()); //大顶堆存放最小堆
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

