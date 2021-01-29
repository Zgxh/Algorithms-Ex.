//你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row,
// col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你
//每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。 
//
// 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。 
//
// 请你返回从左上角走到右下角的最小 体力消耗值 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
//输出：2
//解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
//这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
//输出：1
//解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
// 
//
// 示例 3： 
//
// 
//输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//输出：0
//解释：上图所示路径不需要消耗任何体力。
// 
//
// 
//
// 提示： 
//
// 
// rows == heights.length 
// columns == heights[i].length 
// 1 <= rows, columns <= 100 
// 1 <= heights[i][j] <= 106 
// 
// Related Topics 深度优先搜索 并查集 图 二分查找 
// 👍 141 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // dijkstra 算法
    private int[] dx = {0, 0, -1, 1};
    private int[] dy = {-1, 1, 0, 0};

    public int minimumEffortPath(int[][] heights) {
        int iLen = heights.length, jLen = heights[0].length;
        // 小顶堆，每次挑选出最小边
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((o1, o2) -> o1[2] - o2[2]);
        minHeap.offer(new int[]{0, 0, 0});
        int[][] dist = new int[iLen][jLen];
        for (int i = 0; i < iLen; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        boolean[][] visited = new boolean[iLen][jLen];
        while (!minHeap.isEmpty()) {
            int[] edge = minHeap.poll();
            int x = edge[0], y = edge[1], d = edge[2];
            if (visited[x][y]) {
                continue;
            }
            if (x == iLen - 1 && y == jLen - 1) { // 到达终点
                break;
            }
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int xNew = x + dx[i], yNew = y + dy[i];
                if (xNew >= 0 && xNew < iLen && yNew >= 0 && yNew < jLen && !visited[xNew][yNew]
                        // 若已经有更小的边了，就不用再添加这条边了，防止dist被更新
                        && Math.max(d, Math.abs(heights[x][y] - heights[xNew][yNew])) < dist[xNew][yNew]) {
                    dist[xNew][yNew] = Math.max(d, Math.abs(heights[x][y] - heights[xNew][yNew]));
                    minHeap.offer(new int[]{xNew, yNew, dist[xNew][yNew]});
                }
            }
        }

        return dist[iLen - 1][jLen - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
