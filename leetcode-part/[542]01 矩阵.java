//给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。 
//
// 两个相邻元素间的距离为 1 。 
//
// 示例 1: 
//输入: 
//
// 
//0 0 0
//0 1 0
//0 0 0
// 
//
// 输出: 
//
// 
//0 0 0
//0 1 0
//0 0 0
// 
//
// 示例 2: 
//输入: 
//
// 
//0 0 0
//0 1 0
//1 1 1
// 
//
// 输出: 
//
// 
//0 0 0
//0 1 0
//1 2 1
// 
//
// 注意: 
//
// 
// 给定矩阵的元素个数不超过 10000。 
// 给定矩阵中至少有一个元素是 0。 
// 矩阵中的元素只在四个方向上相邻: 上、下、左、右。 
// 
// Related Topics 深度优先搜索 广度优先搜索


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 广度优先遍历。利用队列，先把所有的0放进去，然后依次弹出每个队头元素，并加入他之前没被遍历过的
     * 一阶近邻，其中队列元素维护的是一个int[]存放矩阵坐标。
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {

        int xLen = matrix.length;
        int yLen = matrix[0].length;
        boolean[][] isVisited = new boolean[xLen][yLen];
        LinkedList<int[]> queue = new LinkedList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int count = 0;

        // 把0放进去
        for (int i = 0; i <xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                if (matrix[i][j] == 0) {
                    isVisited[i][j] = true;
                    queue.addLast(new int[]{i, j});
                    count++;
                }
            }
        }

        // 广度优先遍历
        while (!queue.isEmpty() && count < xLen * yLen) {
            int[] temp = queue.removeFirst();
            for (int i = 0; i < 4; i++) {
                int x = temp[0] + dx[i];
                int y = temp[1] + dy[i];
                if (x >= 0 && x < xLen && y >= 0 && y < yLen && !isVisited[x][y]) {
                    isVisited[x][y] = true;
                    matrix[x][y] = matrix[temp[0]][temp[1]] + 1;
                    queue.addLast(new int[]{x, y});
                    count++;
                }
            }
        }

        return matrix;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
