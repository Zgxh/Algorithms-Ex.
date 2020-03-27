//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 说明： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 
// Related Topics 堆 哈希表


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 先用hashmap计数，O(n)，然后minheap筛选，堆最大深度为k+1，n*log(k+1)，总时间复杂度 n*log(k+1)
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer> result = new LinkedList<>();
        Map<Integer, Integer> count = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry entry : count.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        while (minHeap.size() > 0) {
            result.add(minHeap.poll().getKey());
        }
        Collections.reverse(result); // minheap出来是递增序的，反转成递减序

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
