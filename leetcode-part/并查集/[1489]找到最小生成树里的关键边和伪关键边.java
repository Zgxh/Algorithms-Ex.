//给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi, we
//ighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权
//值和最小。 
//
// 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在
//某些最小生成树中但不会出现在所有最小生成树中的边。 
//
// 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
//输出：[[0,1],[2,3,4,5]]
//解释：上图描述了给定图。
//下图是所有的最小生成树。
//
//注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
//边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
// 
//
// 示例 2 ： 
//
// 
//
// 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
//输出：[[],[0,1,2,3]]
//解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= n <= 100 
// 1 <= edges.length <= min(200, n * (n - 1) / 2) 
// edges[i].length == 3 
// 0 <= fromi < toi < n 
// 1 <= weighti <= 1000 
// 所有 (fromi, toi) 数对都是互不相同的。 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 97 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // kruskal 算法 + 枚举
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int len = edges.length;
        // 创建新数组，保存边的index，防止排序丢失下标
        int[][] newEdges = new int[len][4];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, (o1, o2) -> o1[2] - o2[2]);
        // 通过 kruskal 算法找最小生成树，求出最小权值和
        UnionFind uf = new UnionFind(n);
        int minValue = 0;
        for (int[] edge : newEdges) {
            if (uf.union(edge[0], edge[1])) {
                minValue += edge[2];
            }
        }
        List<List<Integer>> result = new ArrayList();
        result.add(new ArrayList()); // 关键边集合
        result.add(new ArrayList()); // 伪关键边集合
        if (uf.setCount != 1) {
            return result;
        }
        // 枚举每一条边，利用 kruskal 算法判断该边是否为关键边或伪关键边
        for (int i = 0; i < len; i++) {
            // 判断是否为关键边
            UnionFind ufSet = new UnionFind(n);
            int value = 0;
            for (int[] edge : newEdges) {
                if (edge[3] != newEdges[i][3] && ufSet.union(edge[0], edge[1])) {
                    value += edge[2];
                }
            }
            if (ufSet.setCount != 1 || (ufSet.setCount == 1 && value > minValue)) {
                result.get(0).add(newEdges[i][3]);
                continue;
            }
            // 判断是否为伪关键边:不加这条边是最小生成树，加了这条边也是最小生成树
            ufSet = new UnionFind(n);
            value = 0;
            ufSet.union(newEdges[i][0], newEdges[i][1]);
            value += newEdges[i][2];
            for (int[] edge : newEdges) {
                if (edge[3] != newEdges[i][3] && ufSet.union(edge[0], edge[1])) {
                    value += edge[2];
                }
            }
            if (ufSet.setCount == 1 && value == minValue) {
                result.get(1).add(newEdges[i][3]);
            }
        }

        return result;
    }
}

class UnionFind {
    int[] parent;
    int n;
    int setCount;

    UnionFind(int n) {
        this.n = n;
        this.setCount = n;
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }

    public boolean union(int i, int j) {
        int root1 = find(i), root2 = find(j);
        if (root1 != root2) {
            parent[root1] = root2;
            this.setCount--;
            return true;
        }

        return false;
    }

    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
