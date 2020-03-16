import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=218 lang=java
 *
 * [218] 天际线问题
 */
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (buildings == null || buildings.length == 0) {
            return result;
        }
        // 建堆并使用【lambda表达式】定义比较器: 先按pos从小到大排序，若pos相等，再把height从小到大排序 -> 最终建立了以横坐标排序的小顶堆
        PriorityQueue<Point> heap = new PriorityQueue<Point>((c1, c2) -> {
            if (c1.pos != c2.pos) {
                return c1.pos - c2.pos;
            }
            if (c1.height != c2.height) {
                return c1.height - c2.height;
            }
            return 0;
        });
        // 把所有建筑物的(左上角，右上角)加入到堆中，自动按照比较器进行排序储存
        for (int i = 0; i < buildings.length; i++) {
            heap.add(new Point(buildings[i][0], -buildings[i][2])); // 左上角坐标， height用负值表示
            heap.add(new Point(buildings[i][1], buildings[i][2])); // 右上角坐标
        }
        // 建立大顶堆存放历史 height， 堆顶是当前最大高度
        PriorityQueue<Integer> maxHeightHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        // 初始化最大高度为 0 ,即为地面
        maxHeightHeap.add(0);
        int lastPeak = maxHeightHeap.peek();
        while (!heap.isEmpty()) {
            // 依次按照从左往右扫描，遇到左端点就把【正高度】放入，遇到同一建筑物的右端点就把之前对应的【正高度】拿走
            Point point = heap.poll();
            if (point.height < 0) {
                maxHeightHeap.add(-point.height);
            } else {
                maxHeightHeap.remove(point.height);
            }
            // 如果当前最大高度发生改变，则需要往结果中添加天际线转折点
            int currentPeak = maxHeightHeap.peek();
            if (currentPeak != lastPeak) {
                ArrayList<Integer> pointCoordinate = new ArrayList<Integer>();
                pointCoordinate.add(point.pos);
                pointCoordinate.add(currentPeak);
                result.add(pointCoordinate);
                lastPeak = currentPeak;
            }
        }
        return result;
    }
    class Point {
        int pos, height;
        Point(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }
    }
}

