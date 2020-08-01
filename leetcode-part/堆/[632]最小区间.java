//你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。 
//
// 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。 
//
// 示例 1: 
//
// 
//输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
//输出: [20,24]
//解释: 
//列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
//列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
//列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
// 
//
// 注意: 
//
// 
// 给定的列表可能包含重复元素，所以在这里升序表示 >= 。 
// 1 <= k <= 3500 
// -105 <= 元素的值 <= 105 
// 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。 
// 
// Related Topics 哈希表 双指针 字符串 
// 👍 130 👎 0


import java.util.List;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：堆
     * 维护一个小根堆，大小为k,包含每个列表中的一个元素，每次弹出堆顶，然后
     * 加入对应列表的下一个元素。同时维护一个max值，记录堆中的最大值。因为
     * 每个列表都是递增排好序的，所以每次进堆的数总比出堆的数字大，因此max值
     * 更新时可以只与当前进堆的数字进行比较。
     * @param nums
     * @return
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        // min heap, remove the minimum and add next
        int max = 0; // 记录小顶堆中的最大值
        int k = nums.size();
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((o1, o2) -> o1[2] - o2[2]); // row, column, value
        for (int i = 0; i < k; i++) {
            int curValue = nums.get(i).get(0);
            minHeap.offer(new int[]{i, 0, curValue});
            max = Math.max(max, curValue);
        }
        int sectionMin = minHeap.peek()[2], sectionMax = max;
        while (true) {
            int[] temp = minHeap.poll();
            if (temp[1] == nums.get(temp[0]).size() - 1) {
                break;
            }
            int offeredValue = nums.get(temp[0]).get(temp[1] + 1);
            minHeap.offer(new int[]{temp[0], temp[1] + 1, offeredValue});
            max = Math.max(max, offeredValue);
            if (max - minHeap.peek()[2] < sectionMax - sectionMin) {
                sectionMin = minHeap.peek()[2];
                sectionMax = max;
            }
        }

        return new int[]{sectionMin, sectionMax};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
