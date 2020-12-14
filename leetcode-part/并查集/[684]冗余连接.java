//在本问题中, 树指的是一个连通且无环的无向图。 
//
// 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属
//于树中已存在的边。 
//
// 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。 
//
// 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
// 
//
// 示例 1： 
//
// 输入: [[1,2], [1,3], [2,3]]
//输出: [2,3]
//解释: 给定的无向图为:
//  1
// / \
//2 - 3
// 
//
// 示例 2： 
//
// 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
//输出: [1,4]
//解释: 给定的无向图为:
//5 - 1 - 2
//    |   |
//    4 - 3
// 
//
// 注意: 
//
// 
// 输入的二维数组大小在 3 到 1000。 
// 二维数组中的整数在1到N之间，其中N是输入数组的大小。 
// 
//
// 更新(2017-09-26): 
//我们已经重新检查了问题描述及测试用例，明确图是无向 图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。 
// Related Topics 树 并查集 图 
// 👍 210 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 并查集做法：一次遍历所有的边，不断更新结点关系；
    // 每次添加新的边时，先利用union-find算法判断该边对应的两个顶点是否已经可达；
    // 如果可达，则该边即为所求；如果不可达，则把该边正常加入顶点集中。

    private int[] parent;

    public int[] findRedundantConnection(int[][] edges) {
        int len;
        if ((len = edges.length) == 0 || edges[0].length == 0) {
            return new int[0];
        }
        // 初始化 parent 数组：每个结点的父节点初始化为自身
        this.parent = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            parent[i] = i;
        }
        // 遍历所有的边，并合并集合
        for (int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            if (!union(node1, node2)) {
                return edge;
            }
        }

        return new int[0];
    }

    // 寻找父节点
    private int find(int node) {
        // 路径压缩
        if (node != parent[node]) {
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }

    // 合并两个不想交的子集：
    // 1. 如果不相交，则合并，返回 true；
    // 2. 如果相交，则返回 false
    private boolean union(int node1, int node2) {
        int root1 = find(node1), root2 = find(node2);
        if (root1 != root2) {
            parent[root1] = root2;
            return true;
        } else {
            return false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
