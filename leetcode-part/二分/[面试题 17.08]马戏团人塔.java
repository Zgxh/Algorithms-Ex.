//有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请
//编写代码计算叠罗汉最多能叠几个人。
//
// 示例：
//
// 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
//输出：6
//解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
//
//
// 提示：
//
//
// height.length == weight.length <= 10000
//
// Related Topics 排序 二分查找 动态规划
// 👍 40 👎 0


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：先把height和weight合在一起，按height进行排序，从而转化为关于weight的【寻找最长上升子序列】的问题。
     * 注意在按height进行排序的过程中，当height相同时，要按weight的递减序进行排，避免在对weight找最大上升子序列过程中
     * 对应到重复的height，从而违背题目要求。
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex(int[] height, int[] weight) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int[][] values = new int[len][2];
        for (int i = 0; i < len; i++) {
            values[i] = new int[]{height[i], weight[i]};
        }
        Arrays.sort(values, new intArrayComparator()); // 对height进行递增排序，遇到相同的height则按weight进行递减排序
        // 下面对values[]中的weight找最长递增子序列的长度，参考第 300 题
        int[] dp = new int[len];
        dp[0] = values[0][1];
        int maxLength = 1;
        for (int i = 0; i < len; i++) {
            if (values[i][1] > dp[maxLength - 1]) {
                maxLength++;
                dp[maxLength - 1] = values[i][1];
            } else {
                int left = 0, right = maxLength - 1;
                while (left < right) {
                    int mid = left + ((right - left) >> 1);
                    if (dp[mid] >= values[i][1]) {
                         right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                dp[left] = values[i][1];
            }
        }

        return maxLength;
    }

    class intArrayComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) { // 当height相同，则按weight递减排序
                return -o1[1] + o2[1];
            } else {
                return o1[0] - o2[0];
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
