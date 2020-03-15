import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=239 lang=java
 *
 * [239] 滑动窗口最大值
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        /**
         * 首先把最前k个放入大顶堆中，找出最大。每次删前一个元素，再加入需要加入的元素，
         * 并重新计算当前的最大值。
         * 存放结果的方式有两种：1.直接建立定长数组进行存放，前提是知道数组长度。
         * 2.用List作为中间容器，注意list.toArray()方法只能转换Object元素，即只能
         * 得到Integer[]数组，需要进行遍历拆箱integer.intValue()得到基本数据类型int
         */
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            heap.add(nums[i]);
        }
        // List<Integer> result = new ArrayList<Integer>();
        int[] output = new int[nums.length - k + 1];
        // result.add(heap.peek());
        output[0] = heap.peek();
        for (int i = k; i < nums.length; i++) {
            heap.remove(nums[i - k]);
            heap.add(nums[i]);
            // result.add(heap.peek());
            output[i - k + 1] = heap.peek();
        }
        // int[] output = new int[result.size()];
        // for (int i = 0; i < result.size(); i++) {
        //     output[i] = result.get(i).intValue();
        // }
        return output;
    }
}

