//给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。 
//
// 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。 
//
// 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。 
//
// 示例 1: 
//
// 
//输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
//输出: [8,12,6,10,10,10]
//解释: 
//如下为给定的树的示意图：
//  0
// / \
//1   2
//   /|\
//  3 4 5
//
//我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5) 
//也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
// 
//
// 说明: 1 <= N <= 10000 
// Related Topics 树 深度优先搜索 
// 👍 133 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 树形DP。
     * 首先dfs计算根节点root=0的目标值。
     * 然后从根结点root=0开始，逐步翻转孩子节点为新的根，然后递归地进行翻转，
     * 计算出所有孩子翻转后的目标值，并存放到结果数组中。
     *
     * 算法是从根结点开始往孩子节点扩散，其中扩散过程是单向的，所以每个节点在
     * dfs函数中只处理了一次，因此时间复杂度O(n)。
     * 空间复杂度 O(n)
     */

    private int[] dp;
    private int[] childNum; // 以i为根的子树的所有结点个数，包含i
    private int[] result;
    private List<List<Integer>> graph;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        dp = new int[N];
        childNum = new int[N];
        result = new int[N];
        graph = new ArrayList();
        // initialize the graph connection
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList());
        }
        for (int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        // 计算根节点0
        initDfs(0, -1);
        // 通过翻转根节点来计算其他结点的值
        rotateDfs(0, -1);

        return result;
    }

    // 首次dfs来计算根节点的结果，result[0]
    private void initDfs(int node, int root) {
        childNum[node] = 1;
        dp[node] = 0;
        for (int child : graph.get(node)) {
            if (child == root) { // 第一次dfs时，树以0号结点为root，因此只算孩子，避免树结点向上遍历
                continue;
            }
            initDfs(child, node);
            dp[node] += dp[child] + childNum[child];
            childNum[node] += childNum[child];
        }
    }

    // 翻转根来进行dfs，逐步获得所有的result结果
    // 某个不是根节点的结点，要想翻转成根节点，首先他的父节点要先变成根节点，所以这是个递归的过程
    private void rotateDfs(int node, int root) {
        result[node] = dp[node];
        for (int child : graph.get(node)) {
            if (child == root) {
                continue;
            }
            // 保存历史值，以便遍历完后恢复
            int oldDpNode = dp[node], oldDpChild = dp[child];
            int oldChildNumNode = childNum[node], oldChildNumChild = dp[child];
            // 换child为根，把node作为child的子结点
            dp[node] -= dp[child] + childNum[child];
            childNum[node] -= childNum[child];
            dp[child] += dp[node] + childNum[node];
            childNum[child] += childNum[node];
            rotateDfs(child, node);
            // 恢复历史值,进入下一个结点
            dp[node] = oldDpNode;
            dp[child] = oldDpChild;
            childNum[node] = oldChildNumNode;
            childNum[child] = oldChildNumChild;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
