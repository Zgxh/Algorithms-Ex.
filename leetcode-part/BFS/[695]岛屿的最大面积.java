//给定一个包含了一些 0 和 1 的非空二维数组 grid 。 
//
// 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 
//0（代表水）包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。) 
//
// 
//
// 示例 1: 
//
// [[0,0,1,0,0,0,0,1,0,0,0,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,1,1,0,1,0,0,0,0,0,0,0,0],
// [0,1,0,0,1,1,0,0,1,0,1,0,0],
// [0,1,0,0,1,1,0,0,1,1,1,0,0],
// [0,0,0,0,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,0,0,0,0,0,0,1,1,0,0,0,0]]
// 
//
// 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。 
//
// 示例 2: 
//
// [[0,0,0,0,0,0,0,0]] 
//
// 对于上面这个给定的矩阵, 返回 0。 
//
// 
//
// 注意: 给定的矩阵grid 的长度和宽度都不超过 50。 
// Related Topics 深度优先搜索 数组


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：BFS
     * 优化了存储空间，使用grid直接存储访问信息，访问过的1被设为0.
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        int iLen = grid.length, jLen = grid[0].length;
        if (iLen == 0 || jLen == 0) {
            return result;
        }
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (grid[i][j] == 1) {
                    int temp = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 0;
                    while (!queue.isEmpty()) {
                        int[] last = queue.poll();
                        temp++;
                        int x = last[0], y = last[1];
                        if (x > 0 && grid[x - 1][y] == 1) {
                            queue.offer(new int[]{x - 1, y});
                            grid[x - 1][y] = 0;
                        }
                        if (x < iLen - 1 && grid[x + 1][y] == 1) {
                            queue.offer(new int[]{x + 1, y});
                            grid[x + 1][y] = 0;
                        }
                        if (y > 0 && grid[x][y - 1] == 1) {
                            queue.offer(new int[]{x, y - 1});
                            grid[x][y - 1] = 0;
                        }
                        if (y < jLen - 1 && grid[x][y + 1] == 1) {
                            queue.offer(new int[]{x, y + 1});
                            grid[x][y + 1] = 0;
                        }
                    }
                    result = Math.max(result, temp);
                }
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
