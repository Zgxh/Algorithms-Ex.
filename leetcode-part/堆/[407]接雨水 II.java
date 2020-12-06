//给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。 
//
// 
//
// 示例： 
//
// 给出如下 3x6 的高度图:
//[
//  [1,4,3,1,3,2],
//  [3,2,1,3,2,4],
//  [2,3,3,2,3,1]
//]
//
//返回 4 。
// 
//
// 
//
// 如上图所示，这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。 
//
// 
//
// 
//
// 下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 110 
// 0 <= heightMap[i][j] <= 20000 
// 
// Related Topics 堆 广度优先搜索 
// 👍 276 👎 0

·
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 堆的解法。
    // 维护小顶堆，初始化时加入最外圈的点，然后逐次弹出最小高度的点，
    // 并把临近的点加入，加入时把临近的点的高度更新为更大的值
    public int trapRainWater(int[][] heightMap) {
        int iLen, jLen;
        if ((iLen = heightMap.length) == 0 || (jLen = heightMap[0].length) == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[iLen][jLen]; // 标记该位置有没有被访问
        // 维护小顶堆
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((o1, o2) -> o1[2] - o2[2]);
        // 初始化时小顶堆中加入四个边界，并标记为已访问
        for (int i = 0; i < iLen; i++) {
            if (!visited[i][0]) {
                minHeap.offer(new int[]{i, 0, heightMap[i][0]});
                visited[i][0] = true;
            }
            if (!visited[i][jLen - 1]) {
                minHeap.offer(new int[]{i, jLen - 1, heightMap[i][jLen - 1]});
                visited[i][jLen - 1] = true;
            }
        }
        for (int j = 0; j < jLen; j++) {
            if (!visited[0][j]) {
                minHeap.offer(new int[]{0, j, heightMap[0][j]});
                visited[0][j] = true;
            }
            if (!visited[iLen - 1][j]) {
                minHeap.offer(new int[]{iLen - 1, j, heightMap[iLen - 1][j]});
                visited[iLen - 1][j] = true;
            }
        }
        // 小顶堆中始终维护的是一个封闭的圈，每次弹出最小的点，然后加入其临近的点
        int result = 0;
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};
        while (!minHeap.isEmpty()) {
            int[] curMin = minHeap.poll();
            for (int k = 0; k < 4; k++) {
                int x = curMin[0] + dx[k], y = curMin[1] + dy[k];
                if (x >= 0 && x < iLen && y >= 0 && y < jLen && !visited[x][y]) {
                    // 如果弹出的点比临近的点还高，说明可以注水到 curMin[2] 高度
                    if (curMin[2] > heightMap[x][y]) {
                        result += curMin[2] - heightMap[x][y];
                    }
                    // 加入弹出的点的临近点，其中高度要更新为相关点的最大高度 Math.max(curMin[2], heightMap[x][y])
                    minHeap.offer(new int[]{x, y, Math.max(curMin[2], heightMap[x][y])});
                    visited[x][y] = true;
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
