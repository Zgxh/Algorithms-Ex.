//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 105 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 745 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 拓扑排序，如果发现了循环依赖，则返回false

    int[] status; // 每个结点的访问状态，0未访问，1访问中，2访问结束
    Map<Integer, List<Integer>> map;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        status = new int[numCourses];
        map = new HashMap();
        for (int[] prerequisite : prerequisites) {
            List<Integer> list = new ArrayList();
            if (map.containsKey(prerequisite[0])) {
                list = map.get(prerequisite[0]);
            }
            list.add(prerequisite[1]);
            map.put(prerequisite[0], list);
        }
        for (int course : map.keySet()) {
            if (canFinishHelp(course) == false) {
                return false;
            }
        }

        return true;
    }

    private boolean canFinishHelp(int course) {
        if (status[course] == 2) {
            return true;
        } else if (status[course] == 1) { // 发现了循环依赖
            return false;
        }
        if (!map.containsKey(course)) {
            status[course] = 2;
            return true;
        }
        status[course] = 1;
        for (int pre : map.get(course)) {
            if (canFinishHelp(pre) == false) {
                return false;
            }
        }
        status[course] = 2;

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
