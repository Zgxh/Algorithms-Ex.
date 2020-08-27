//给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 
//JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。 
//
// 说明: 
//
// 
// 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排
//序更靠前 
// 所有的机场都用三个大写字母表示（机场代码）。 
// 假定所有机票至少存在一种合理的行程。 
// 
//
// 示例 1: 
//
// 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
// 
//
// 示例 2: 
//
// 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。 
// Related Topics 深度优先搜索 图 
// 👍 251 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 欧拉图问题的 Hierholzer 算法。
     * Hierholzer 算法用于在连通图中寻找欧拉路径，其流程如下：
     * 1. 从起点出发，进行深度优先搜索。
     * 2. 每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。
     * 3. 如果没有可移动的路径，则将所在节点加入到栈中，并返回。
     * 只有入度与出度差为 1 的节点会导致死胡同。而该节点必然是最后一个遍历到的节点。
     * 当遍历完一个节点所连的所有节点后，才将该节点入栈（即逆序入栈）。
     * 对于当前节点而言，从它的每一个非「死胡同」分支出发进行深度优先搜索，都将会搜回到当前节点。
     * 而从它的「死胡同」分支出发进行深度优先搜索将不会搜回到当前节点。
     * 也就是说当前节点的死胡同分支将会优先于其他非「死胡同」分支入栈。
     * 这样就能保证我们可以「一笔画」地走完所有边，最终的栈中逆序地保存了「一笔画」的结果。
     * 我们只要将栈中的内容反转，即可得到答案。
     *
     * 其中按字典序遍历边，利用到了堆。
     */
    private Map<String, PriorityQueue<String>> map = new HashMap<>();
    private List<String> itinerary = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
