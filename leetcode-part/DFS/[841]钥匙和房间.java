//有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。 
//
// 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 
//N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。 
//
// 最初，除 0 号房间外的其余所有房间都被锁住。 
//
// 你可以自由地在房间之间来回走动。 
//
// 如果能进入每个房间返回 true，否则返回 false。 
//
// 
// 
//
// 示例 1： 
//
// 输入: [[1],[2],[3],[]]
//输出: true
//解释:  
//我们从 0 号房间开始，拿到钥匙 1。
//之后我们去 1 号房间，拿到钥匙 2。
//然后我们去 2 号房间，拿到钥匙 3。
//最后我们去了 3 号房间。
//由于我们能够进入每个房间，我们返回 true。
// 
//
// 示例 2： 
//
// 输入：[[1,3],[3,0,1],[2],[0]]
//输出：false
//解释：我们不能进入 2 号房间。
// 
//
// 提示： 
//
// 
// 1 <= rooms.length <= 1000 
// 0 <= rooms[i].length <= 1000 
// 所有房间中的钥匙数量总计不超过 3000。 
// 
// Related Topics 深度优先搜索 图 
// 👍 101 👎 0


import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：dfs或bfs
     * 记录可以访问的结点个数，同时记录已经访问过的结点，避免重复访问。
     */

    int canVisit;
    boolean[] visited;
    int len;
    List<List<Integer>> rooms;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        len = rooms.size();
        canVisit = new HashSet<>(len);
        visited = new boolean[len];
        this.rooms = rooms;
        return dfs(0);
    }

    private boolean dfs(int roomIndex) {
        visited[roomIndex] = true;
        canVisit++; // 只有该房间没被访问时才 +1
        for (int next : rooms.get(roomIndex)) {
            if (!visited[next]) {
                if (dfs(next)) {
                    return true;
                }
            }
        }
        if (canVisit == len) {
            return true;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
