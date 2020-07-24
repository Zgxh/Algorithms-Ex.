//在选举中，第 i 张票是在时间为 times[i] 时投给 persons[i] 的。 
//
// 现在，我们想要实现下面的查询函数： TopVotedCandidate.q(int t) 将返回在 t 时刻主导选举的候选人的编号。 
//
// 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。 
//
// 示例： 
//
// 输入：["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,1
//5,20,25,30]],[3],[12],[25],[15],[24],[8]]
//输出：[null,0,1,1,0,0,1]
//解释：
//时间为 3，票数分布情况是 [0]，编号为 0 的候选人领先。
//时间为 12，票数分布情况是 [0,1,1]，编号为 1 的候选人领先。
//时间为 25，票数分布情况是 [0,1,1,0,0,1]，编号为 1 的候选人领先（因为最近的投票结果是平局）。
//在时间 15、24 和 8 处继续执行 3 个查询。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= persons.length = times.length <= 5000 
// 0 <= persons[i] <= persons.length 
// times 是严格递增的数组，所有元素都在 [0, 10^9] 范围中。 
// 每个测试用例最多调用 10000 次 TopVotedCandidate.q。 
// TopVotedCandidate.q(int t) 被调用时总是满足 t >= times[0]。 
// 
// Related Topics 二分查找 
// 👍 19 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class TopVotedCandidate {

    /**
     * 思路：二分查找。
     * 预计算所有时间对应的leader，然后二分查找时间time[]，然后直接在leaders[]中查询当前leader
     */

    private int[] times;
    private int[] leaders;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        int len = times.length;
        this.leaders = new int[len];
        Map<Integer, Integer> map = new HashMap();
        int maxVote = 0, leader = -1;
        for (int i = 0; i < len; i++) {
            int person = persons[i];
            int vote = map.getOrDefault(persons[i], 0) + 1;
            map.put(person, vote);
            if (vote >= maxVote) {
                maxVote = vote;
                leader = person;
            }
            leaders[i] = leader;
        }
    }

    public int q(int t) {
        int left = 0, right = times.length - 1;
        while (left < right) { // 二分查找退出的条件是left==right,此时times[left]>=t
            int mid = left + ((right - left) >> 1);
            if (times[mid] < t) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (times[left] <= t) { // <是为了避免右边界仍然小于目标t，==是因为当天的投票也应该被计算
            left++;
        }

        return leaders[left - 1];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
//leetcode submit region end(Prohibit modification and deletion)
