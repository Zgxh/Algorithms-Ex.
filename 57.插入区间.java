import java.util.ArrayList;

/*
 * @lc app=leetcode.cn id=57 lang=java
 *
 * [57] 插入区间
 */
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<int[]>();
        int length = intervals.length;
        if (intervals == null || length == 0) {
            res.add(newInterval);
            return res.toArray(new int[0][]);
        }
        for (int i = 0; i < length; i++) {
            int[] interval = intervals[i];
            if (newInterval != null && newInterval.length != 0) {
                if (interval[1] < newInterval[0]) {
                    res.add(interval);
                }
                else if (interval[0] > newInterval[1]) {
                    res.add(newInterval);
                    newInterval = null;
                    i--;
                }
                else {
                    newInterval[0] = Math.min(interval[0], newInterval[0]);
                    newInterval[1] = Math.max(interval[1], newInterval[1]);
                }
            }
            else {
                res.add(interval);
            }
        }
        if (newInterval != null) {
            res.add(newInterval);
            newInterval = null;
        }
        return res.toArray(new int[0][]);
    }
}

