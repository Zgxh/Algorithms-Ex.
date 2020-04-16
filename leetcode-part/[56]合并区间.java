//给出一个区间的集合，请合并所有重叠的区间。 
//
// 示例 1: 
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
// Related Topics 排序 数组


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 先把元素按左边界从小到大排序，然后利用栈依次合并区间。
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {

        if (intervals.length == 0) {
            return new int[0][0];
        }

        Arrays.sort(intervals, (o1, o2) -> { // 按0号位置第一顺位，1号位置第二顺位进行排序
            int result = o1[0] - o2[0];
            if (result == 0) {
                return o1[1] - o2[1];
            }
            return result;
        });

        LinkedList<Integer> stack = new LinkedList<>();
        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {
            if (stack.isEmpty()) {
                stack.addFirst(interval[0]);
                stack.addFirst(interval[1]);
            } else if (interval[0] > stack.getFirst()) {
                int right = stack.removeFirst();
                int left = stack.removeFirst();
                result.add(new int[]{left, right});
                stack.addFirst(interval[0]);
                stack.addFirst(interval[1]);
            } else if (interval[0] <= stack.getFirst()) {
                if (interval[1] > stack.getFirst()) {
                    stack.removeFirst();
                    stack.addFirst(interval[1]);
                }
            }
        }
        // 最后一组剩下的拿出来加入结果中，注意先判断栈是不是空
        int right = stack.removeFirst();
        int left = stack.removeFirst();
        result.add(new int[]{left, right});

        return result.toArray(new int[0][0]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
