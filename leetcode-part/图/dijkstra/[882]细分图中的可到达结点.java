//从具有 0 到 N-1 的结点的无向图（“原始图”）开始，对一些边进行细分。 
//
// 该图给出如下：edges[k] 是整数对 (i, j, n) 组成的列表，使 (i, j) 是原始图的边。 
//
// n 是该边上新结点的总数 
//
// 然后，将边 (i, j) 从原始图中删除，将 n 个新结点 (x_1, x_2, ..., x_n) 添加到原始图中， 
//
// 将 n+1 条新边 (i, x_1), (x_1, x_2), (x_2, x_3), ..., (x_{n-1}, x_n), (x_n, j) 添加到
//原始图中。 
//
// 现在，你将从原始图中的结点 0 处出发，并且每次移动，你都将沿着一条边行进。 
//
// 返回最多 M 次移动可以达到的结点数。 
//
// 
//
// 示例 1： 
//
// 输入：edges = [[0,1,10],[0,2,1],[1,2,2]], M = 6, N = 3
//输出：13
//解释：
//在 M = 6 次移动之后在最终图中可到达的结点如下所示。
//
// 
//
// 示例 2： 
//
// 输入：edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], M = 10, N = 4
//输出：23 
//
// 
//
// 提示： 
//
// 
// 0 <= edges.length <= 10000 
// 0 <= edges[i][0] < edges[i][1] < N 
// 不存在任何 i != j 情况下 edges[i][0] == edges[j][0] 且 edges[i][1] == edges[j][1]. 
// 原始图没有平行的边。 
// 0 <= edges[i][2] <= 10000 
// 0 <= M <= 10^9 
// 1 <= N <= 3000 
// 可到达结点是可以从结点 0 开始使用最多 M 次移动到达的结点。 
// 
//
// 
// Related Topics 堆 
// 👍 29 👎 0


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 使用 dijkstra 算法
    // 给出定义：[新结点 newNode]：指夹在两个结点中间的新增的结点，它们不属于原有的N个结点
    public int reachableNodes(int[][] edges, int M, int N) {
        // 建图： node -> map, 其中map：neighbor -> newNodeCount
        Map<Integer, Map<Integer, Integer>> graph = new HashMap();
        for (int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            int newNodeCount = edge[2];
            // Map<Integer, Integer> map1 = graph.getOrDefault(node1, new HashMap());
            // map1.put(node2, newNodeCount);
            // Map<Integer, Integer> map2 = graph.getOrDefault(node2, new HashMap());
            // map2.put(node1, newNodeCount);
            // graph.put(node1, map1);
            // graph.put(node2, map2);
            // 此两行代码与上面的效果是一样的
            graph.computeIfAbsent(node1, x->new HashMap()).put(node2, newNodeCount);
            graph.computeIfAbsent(node2, x->new HashMap()).put(node1, newNodeCount);
        }

        // 小顶堆，到达某结点步数越小，则越先访问
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>((o1, o2) -> o1.dist - o2.dist);
        // 从 0 号结点出发
        minHeap.offer(new Node(0, 0));

        // 存放每个结点 node，从0号结点开始，到达 node 所需要的最小步数
        Map<Integer, Integer> dist = new HashMap();
        dist.put(0, 0);

        // 存放访问过的结点 node，往 neighbor 方向前进时，在总步数 M 步内可以达到的 [新结点] 的个数
        // 映射关系: nodeIndex * N + neighbor
        Map<Integer, Integer> visitedNewNodeCount = new HashMap();

        int result = 0;
        while (!minHeap.isEmpty()) {
            Node minDistNode = minHeap.poll();
            int node = minDistNode.node;
            int curStep = minDistNode.dist;
            // 之前遍历所有 neighbor 时，可能同时到达了某一结点，应按该结点的最早到达时间，且只计算一次
            if (curStep > dist.getOrDefault(node, 0)) {
                continue;
            }
            result++;
            // 如果该结点没有其他邻接顶点，则直接返回
            if (!graph.containsKey(node)) {
                continue;
            }
            // 遍历这个图结点的所有邻接顶点
            for (int neighbor : graph.get(node).keySet()) {
                // 从 node 出发，往 neighbor 方向，有多少个中间结点
                int newNodeCount = graph.get(node).get(neighbor);
                // 在当前边（node -> neighbor）上最多走多远，并记录
                int maxArrival = Math.min(newNodeCount, M - curStep);
                visitedNewNodeCount.put(node * N + neighbor, maxArrival);
                // 到达该 neighbor 时的步数
                int newStep = curStep + newNodeCount + 1;
                if (newStep < dist.getOrDefault(neighbor, M + 1)) {
                    minHeap.offer(new Node(neighbor, newStep));
                    dist.put(neighbor, newStep);
                }
            }
        }

        for (int[] edge : edges) {
            result += Math.min(edge[2], visitedNewNodeCount.getOrDefault(edge[0] * N + edge[1], 0) +
                    visitedNewNodeCount.getOrDefault(edge[1] * N + edge[0], 0));
        }

        return result;
    }
}

class Node {
    int node;
    int dist;

    Node(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
