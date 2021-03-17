//有 n 个网络节点，标记为 1 到 n。 
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
//i 是一个信号从源节点传递到目标节点的时间。 
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= n <= 100 
// 1 <= times.length <= 6000 
// times[i].length == 3 
// 1 <= ui, vi <= n 
// ui != vi 
// 0 <= wi <= 100 
// 所有 (ui, vi) 对都 互不相同（即，不含重复边） 
// 
// Related Topics 堆 深度优先搜索 广度优先搜索 图 
// 👍 235 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // dijkstra
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] time = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(time[i], 20000);
        }
        for (int[] arr : times) {
            int i = arr[0], j = arr[1];
            int interval = arr[2];
            time[i - 1][j - 1] = interval;
        }
        int max = 0;
        boolean[] visited = new boolean[n];
        int count = 1;
        visited[k - 1] = true;
        int start = k - 1;
        while (count < n) {
            int minNode = -1;
            int min = 20000;
            // 找出未遍历的结点中距离start最近的
            for (int i = 0; i < n; i++) {
                if (!visited[i] && time[start][i] < min) {
                    min = time[start][i];
                    minNode = i;
                }
            }
            if (min == 20000) {
                return -1;
            }
            visited[minNode] = true;
            count++;
            max = Math.max(max, time[start][minNode]);
            for (int i = 0; i < n; i++) {
                if (!visited[i] && time[start][minNode] + time[minNode][i] < time[start][i]) {
                    time[start][i] = time[start][minNode] + time[minNode][i];
                }
            }
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
