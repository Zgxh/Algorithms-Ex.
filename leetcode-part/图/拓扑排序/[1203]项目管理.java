//有 n 个项目，每个项目或者不属于任何小组，或者属于 m 个小组之一。group[i] 表示第 i 个项目所属的小组，如果第 i 个项目不属于任何小组，则 
//group[i] 等于 -1。项目和小组都是从零开始编号的。可能存在小组不负责任何项目，即没有任何项目属于这个小组。 
//
// 请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表： 
//
// 
// 同一小组的项目，排序后在列表中彼此相邻。 
// 项目之间存在一定的依赖关系，我们用一个列表 beforeItems 来表示，其中 beforeItems[i] 表示在进行第 i 个项目前（位于第 i 个
//项目左侧）应该完成的所有项目。 
// 
//
// 如果存在多个解决方案，只需要返回其中任意一个即可。如果没有合适的解决方案，就请返回一个 空列表 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[
//3,6],[],[],[]]
//输出：[6,3,4,1,5,2,0,7]
// 
//
// 示例 2： 
//
// 
//输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[
//3],[],[4],[]]
//输出：[]
//解释：与示例 1 大致相同，但是在排序后的列表中，4 必须放在 6 的前面。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= m <= n <= 3 * 104 
// group.length == beforeItems.length == n 
// -1 <= group[i] <= m - 1 
// 0 <= beforeItems[i].length <= n - 1 
// 0 <= beforeItems[i][j] <= n - 1 
// i != beforeItems[i][j] 
// beforeItems[i] 不含重复元素 
// 
// Related Topics 深度优先搜索 图 拓扑排序 
// 👍 157 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 拓扑排序：
     * 题意是要求同一组的必须排在一块，组号为-1的自己一组。
     * 最后的结果是项目和组号都要按照拓扑排序的要求。
     *
     * @param n
     * @param m
     * @param group
     * @param beforeItems
     * @return
     */
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 给组号为-1的项目重新编号
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }
        // 初始化项目邻接表、组邻接表、入度数组
        List<Integer>[] itemAdj = new ArrayList[n];
        List<Integer>[] groupAdj = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            groupAdj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            itemAdj[i] = new ArrayList<>();
        }
        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];
        // 建图、更新入度数组
        for (int i = 0; i < n; i++) {
            int curGroup = group[i];
            for (int beforeItem : beforeItems.get(i)) {
                // item
                itemAdj[beforeItem].add(i);
                itemIndegree[i]++;
                // 组
                int beforeGroup = group[beforeItem];
                if (beforeGroup != curGroup) {
                    groupAdj[beforeGroup].add(curGroup);
                    groupIndegree[curGroup]++;
                }
            }
        }
        // 拓扑排序：对 item 和 group
        List<Integer> itemList = topoligicalSort(itemAdj, itemIndegree, n);
        if (itemList.size() == 0) {
            return new int[0];
        }
        List<Integer> groupList = topoligicalSort(groupAdj, groupIndegree, m);
        if (groupList.size() == 0) {
            return new int[0];
        }
        // 把拓扑排序后的 item 按组重新排列，同一组的挨着
        Map<Integer, List<Integer>> map = new HashMap();
        for (int item : itemList) {
            map.computeIfAbsent(group[item], (o) -> new ArrayList()).add(item);
        }
        // 按照组拓扑排序的顺序，对应到item的最终排序结果
        List<Integer> result = new ArrayList();
        for (int groupId : groupList) {
            result.addAll(map.getOrDefault(groupId, new ArrayList()));
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // 拓扑排序：当某结点没有前驱结点，即入度为0时，就入队，可以进行拓扑排序
    private List<Integer> topoligicalSort(List<Integer>[] adj, int[] indegree, int n) {
        List<Integer> result = new ArrayList();
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int front = queue.poll();
            result.add(front);
            for (int successor : adj[front]) {
                indegree[successor]--;
                if (indegree[successor] == 0) {
                    queue.offer(successor);
                }
            }
        }
        if (result.size() == n) {
            return result;
        }

        return new ArrayList(); // 此时代表没有符合要求的结果
    }
}
//leetcode submit region end(Prohibit modification and deletion)
