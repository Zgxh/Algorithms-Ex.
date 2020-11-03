//给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑
//充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。 
//
// 示例 1: 
//
// 输入: [[0, 30],[5, 10],[15, 20]]
//输出: 2 
//
// 示例 2: 
//
// 输入: [[7,10],[2,4]]
//输出: 1 
// Related Topics 堆 贪心算法 排序 
// 👍 188 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 排序 + 小顶堆
     * 采用堆来存放所有的会议室信息，每当不能满足要求了，就新创建一个会议室 node
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0]);
        PriorityQueue<Node> minHeap = new PriorityQueue();
        int index = 0;
        for (int[] interval : intervals) {
            // 当结束时间最早的会议室也不能安排下这场会议了，就新建一个会议室结点
            if (minHeap.isEmpty() || minHeap.peek().endTime > interval[0]) {
                minHeap.offer(new Node(index, interval[1]));
                index++;
            } else { // 总是采用最节省空间的方式来将该会议加入到会议室
                Node temp = minHeap.poll();
                temp.endTime = interval[1];
                minHeap.offer(temp);
            }
        }

        return index;
    }
}

/**
 * 会议室结点，
 */
class Node implements Comparable<Node> {
    int index; // 会议室编号
    int endTime; // 该会议室中当前已安排的会议的结束时间
    Node(int index, int endTime) {
        this.index = index;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Node node) {
        return this.endTime - node.endTime;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
