//有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是： 
//
// 
// 一块砖直接连接到网格的顶部，或者 
// 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时 
// 
//
// 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消
//失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。 
//
// 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。 
//
// 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
//输出：[2]
//解释：
//网格开始为：
//[[1,0,0,0]，
// [1,1,1,0]]
//消除 (1,0) 处加粗的砖块，得到网格：
//[[1,0,0,0]
// [0,1,1,0]]
//两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
//[[1,0,0,0],
// [0,0,0,0]]
//因此，结果为 [2] 。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
//输出：[0,0]
//解释：
//网格开始为：
//[[1,0,0,0],
// [1,1,0,0]]
//消除 (1,1) 处加粗的砖块，得到网格：
//[[1,0,0,0],
// [1,0,0,0]]
//剩下的砖都很稳定，所以不会掉落。网格保持不变：
//[[1,0,0,0], 
// [1,0,0,0]]
//接下来消除 (1,0) 处加粗的砖块，得到网格：
//[[1,0,0,0],
// [0,0,0,0]]
//剩下的砖块仍然是稳定的，所以不会有砖块掉落。
//因此，结果为 [0,0] 。 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// grid[i][j] 为 0 或 1 
// 1 <= hits.length <= 4 * 104 
// hits[i].length == 2 
// 0 <= xi <= m - 1 
// 0 <= yi <= n - 1 
// 所有 (xi, yi) 互不相同 
// 
// Related Topics 并查集 
// 👍 177 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 并查集。
    // 把砖块的拆除倒过来，转化为砖块的添加，即等价为并查集的合并操作
    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int iLen = grid.length, jLen = grid[0].length;
        int[][] copy = new int[iLen][jLen]; // 用来存放敲掉后的状态
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        // 依次敲掉砖块
        for (int[] hit : hits) {
            copy[hit[0]][hit[1]] = 0;
        }
        // 建立并查集
        int size = iLen * jLen;
        UnionFind uf = new UnionFind(size + 1);
        // 连接第一行和底座
        for (int j = 0; j < jLen; j++) {
            if (copy[0][j] == 1) {
                uf.union(j, size); // size 代表底座
            }
        }
        // 连接第二行往下的
        for (int i = 1; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (copy[i][j] == 1) {
                    if (copy[i - 1][j] == 1) { // 向上连接
                        uf.union((i - 1) * jLen + j, i * jLen + j);
                    }
                    if (j > 0 && copy[i][j - 1] == 1) { // 向左连接
                        uf.union(i * jLen + j - 1, i * jLen + j);
                    }
                }
            }
        }
        // 从后至前依次补上砖块
        int hitsLen = hits.length;
        int[] result = new int[hitsLen];
        for (int i = hitsLen - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == 0) {
                continue;
            }
            int preSize = uf.getSize(size); // 修补前连接底座的砖块数
            if (x == 0) {
                uf.union(y, size);
            }
            // 连接[x,y]与其四个方向的砖块为同一棵树
            for (int k = 0; k < 4; k++) {
                int xNew = x + dx[k], yNew = y + dy[k];
                if (xNew >= 0 && xNew < iLen && yNew >= 0 && yNew < jLen && copy[xNew][yNew] == 1) {
                    uf.union(x * jLen + y, xNew * jLen + yNew);
                }
            }
            int curSize = uf.getSize(size); // 修补后连接底座的砖块数
            result[i] = Math.max(curSize - preSize - 1, 0); // -1 是因为当前被拆掉的砖块不算，与0取max是避免砖块连接没变化时出现负数
            copy[x][y] = 1;
        }

        return result;
    }
}

class UnionFind {
    private int[] parent;
    private int[] size; // size[i]: 以结点i为根的子树的结点总数

    public UnionFind(int n) {
        this.parent = new int[n];
        this.size = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }

    public void union(int i, int j) {
        int root1 = find(i), root2 = find(j);
        if (root1 != root2) {
            parent[root1] = root2;
            size[root2] += size[root1];
        }
    }

    // 查找node所在的树上的结点个数
    public int getSize(int node) {
        int root = find(node);
        return size[root];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
