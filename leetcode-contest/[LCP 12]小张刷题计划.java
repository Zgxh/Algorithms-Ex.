//为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，编号从 0 到 n-1，并计划在 m 天内按
//照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。 
//
// 在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i 的题目。此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题
//的做题时间。为了防止“小张刷题计划”变成“小杨刷题计划”，小张每天最多使用一次求助。 
//
// 我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。 
//
// 示例 1： 
//
// 
// 输入：time = [1,2,3,3], m = 2 
//
// 输出：3 
//
// 解释：第一天小张完成前三题，其中第三题找小杨帮忙；第二天完成第四题，并且找小杨帮忙。这样做题时间最多的一天花费了 3 的时间，并且这个值是最小的。 
// 
//
// 示例 2： 
//
// 
// 输入：time = [999,999,999], m = 4 
//
// 输出：0 
//
// 解释：在前三天中，小张每天求助小杨一次，这样他可以在三天内完成所有的题目并不花任何时间。 
// 
//
// 
//
// 限制： 
//
// 
// 1 <= time.length <= 10^5 
// 1 <= time[i] <= 10000 
// 1 <= m <= 1000 
// 
//


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 对time的总和进行二分，找到一个合适的limit，能够恰好划分为m组，
     * 且limit最小
     * @param time
     * @param m
     * @return
     */
    public int minTime(int[] time, int m) {

        int len = time.length;
        int left = Integer.MAX_VALUE, right = 0, mid;
        int res = 0;

        if (m >= len) { // 直接变成小杨刷题计划
            return 0;
        }

        // left为time[]的min, right为time[]的和
        for (int i = 0; i < len; i++) {
            left = Math.min(left, time[i]);
            right += time[i];
        }

        // 二分求结果
        while (left <= right) {
            mid = (left + right) >> 1;
            if (moreThanM(time, len, mid, m)) { // 划分出来超过了m组，需要调大limit
                left = mid + 1;
            } else { // 划分出来小于m组，需要调小limit
                right = mid - 1;
                res = mid;
            }
        }

        /**
         * 最后res一定会缩到time[]的某一段序列求和，因为二分终止的条件是left=right，
         * 而moreThanM()函数在计算分段时，每超过limit才会增加段数，这个条件决定了
         * res收敛时一定正好等于time[]某段的加和。
         */
        return res;
    }

    /**
     * 验证用此limit划分完是否超过m组
     * @param time
     * @param len
     * @param limit
     * @param m
     * @return
     */
    private boolean moreThanM(int[] time, int len, int limit, int m) {

        int cnt = 1; // 记录划分的组数
        int sum = time[0], maxVal = time[0];

        for (int i = 1; i < len; i++) {
            sum += time[i];
            maxVal = Math.max(maxVal, time[i]);
            if (sum - maxVal > limit) { // 每组的求和每超过limit，就新划分出一组
                cnt++;
                maxVal = sum = time[i];
            }
            if (cnt > m) // 当划分的子数组个数超过m时，直接返回true
                return true;
        }
        return false; // 此时找到一种可能的分割方案，可以继续缩小limit
    }
}
//leetcode submit region end(Prohibit modification and deletion)
