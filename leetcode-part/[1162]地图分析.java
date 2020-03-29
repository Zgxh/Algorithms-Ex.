//你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，
//你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。 
//
// 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x
//1| + |y0 - y1| 。 
//
// 如果我们的地图上只有陆地或者海洋，请返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[1,0,1],[0,0,0],[1,0,1]]
//输出：2
//解释： 
//海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
// 
//
// 示例 2： 
//
// 
//
// 输入：[[1,0,0],[0,0,0],[0,0,0]]
//输出：4
//解释： 
//海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length == grid[0].length <= 100 
// grid[i][j] 不是 0 就是 1 
// 
// Related Topics 广度优先搜索 图


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：多源BFS：从所有的陆地开始，每次向外扩展一格，并修改grid数组（存放当前层数），
     * 利用队列，就像二叉树的BFS（层次遍历）那样，每次把下一层要遍历的加入队列，直到最后队列
     * 为空，BFS完成。
     * @param grid
     * @return
     */
    public int maxDistance(int[][] grid) {

        int iLen = grid.length, jLen = grid[0].length;
        Queue<int[]> queue = new LinkedList<>(); // 存放每一层BFS的坐标
        int[] curIndex = new int[2];
        int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0}; // 每次的移动量

        // 初始化：先把陆地的坐标放入
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 没有陆地或海洋
        if (queue.size() == 0 || queue.size() == iLen * jLen) {
            return -1;
        }

        // 多源BFS
        while (!queue.isEmpty()) {
            curIndex = queue.poll();
            int x = curIndex[0], y = curIndex[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i], newY = y + dy[i];
                if (newX < 0 || newX >= iLen || newY < 0 || newY >= jLen || grid[newX][newY] != 0) {
                    continue;
                }
                queue.offer(new int[]{newX, newY});
                grid[newX][newY] = grid[x][y] + 1;
            }
        }

        return grid[curIndex[0]][curIndex[1]] - 1; // 初始的陆地是1，减一就是距离
    }
}
//leetcode submit region end(Prohibit modification and deletion)
