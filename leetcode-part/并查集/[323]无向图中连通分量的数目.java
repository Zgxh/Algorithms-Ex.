//给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。 
//
// 示例 1: 
//
// 输入: n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]
//
//     0          3
//     |          |
//     1 --- 2    4 
//
//输出: 2
// 
//
// 示例 2: 
//
// 输入: n = 5 和 edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
//
//     0           4
//     |           |
//     1 --- 2 --- 3
//
//输出:  1
// 
//
// 注意: 
//你可以假设在 edges 中不会出现重复的边。而且由于所以的边都是无向边，[0, 1] 与 [1, 0] 相同，所以它们不会同时在 edges 中出现。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 
// 👍 61 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // union-find 算法

    private int[] parents;

    public int countComponents(int n, int[][] edges) {
        this.parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int[] edge : edges) {
            int child = edge[0], father = edge[1];
            union(child, father);
        }
        Set<Integer> set = new HashSet();
        for (int i = 0; i < n; i++) {
            int father = find(i);
            set.add(father);
        }

        return set.size();
    }

    private void union(int node1, int node2) {
        int root1 = find(node1), root2 = find(node2);
        if (root1 != root2) {
            parents[root1] = root2;
        }
    }

    private int find(int node) {
        if (node != parents[node]) {
            parents[node] = find(parents[node]);
        }

        return parents[node];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
