import java.util.Arrays;
import java.util.LinkedList;

/*
 * @lc app=leetcode.cn id=56 lang=java
 *
 * [56] 合并区间
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<int[]>();
        // if (intervals == null || intervals.length == 0) {
        //     return res.toArray();
        // }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int[] interval : intervals) {
            if (res.isEmpty() || res.getLast()[1] < interval[0]) {
                res.add(interval);
            }
            else {
                res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
            }
        }
        return res.toArray(new int[0][0]);
    }
}

