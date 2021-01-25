//给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。 
//
// 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 
//val 的绝对值。 
//
// 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//输出：20
//解释：
//
//我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
//注意到任意两个点之间只有唯一一条路径互相到达。
// 
//
// 示例 2： 
//
// 
//输入：points = [[3,12],[-2,5],[-4,1]]
//输出：18
// 
//
// 示例 3： 
//
// 
//输入：points = [[0,0],[1,1],[1,0],[-1,1]]
//输出：4
// 
//
// 示例 4： 
//
// 
//输入：points = [[-1000000,-1000000],[1000000,1000000]]
//输出：4000000
// 
//
// 示例 5： 
//
// 
//输入：points = [[0,0]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= points.length <= 1000 
// -106 <= xi, yi <= 106 
// 所有点 (xi, yi) 两两不同。 
// 
// Related Topics 并查集 
// 👍 124 👎 0


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 并查集
    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        DisjointSet disjointSet = new DisjointSet(len);
        List<Edge> edges = new ArrayList();
        // 计算任意两边之间的曼哈顿距离，并放入集合中
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int dist = dist(points, i, j);
                edges.add(new Edge(i, j, dist));
            }
        }
        // kruskal 算法找最小联通子图
        Collections.sort(edges, (o1, o2) -> o1.len - o2.len);
        int result = 0;
        int index = 1;
        for (Edge edge : edges) {
            int i = edge.i, j = edge.j;
            int dist = edge.len;
            if (disjointSet.union(i, j)) {
                result += dist;
                index++;
                if (index == len) {
                    break;
                }
            }
        }

        return result;
    }

    private int dist(int[][] points, int i, int j) {
        return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }
}

class Edge {
    int i;
    int j;
    int len;

    Edge(int i, int j, int len) {
        this.i = i;
        this.j = j;
        this.len = len;
    }
}

class DisjointSet {
    int[] parents;

    DisjointSet(int len) {
        this.parents = new int[len];
        for (int i = 0; i < len; i++) {
            parents[i] = i;
        }
    }

    int find(int node) {
        if (parents[node] != node) {
            parents[node] = find(parents[node]);
        }

        return parents[node];
    }

    boolean union(int i, int j) {
        int root1 = find(i), root2 = find(j);
        if (root1 == root2) {
            return false;
        }
        parents[root1] = root2;

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
