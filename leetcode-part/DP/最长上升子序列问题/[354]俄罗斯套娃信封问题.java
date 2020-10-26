//给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如
//同俄罗斯套娃一样。 
//
// 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。 
//
// 说明: 
//不允许旋转信封。 
//
// 示例: 
//
// 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出: 3 
//解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
// 
// Related Topics 二分查找 动态规划 
// 👍 217 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 排序 + 最长递增子序列
    // 对第一维进行排序，然后对第二维寻找最长递增子序列
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }
        // 第一维非递减序排序
        // 第二维非递增排序，这样是为了防止第一维的 W 出现相等的情况，而在按第二维计算递增子序列时多次使用同一个 W
        Arrays.sort(envelopes, (o1, o2) -> o1[0] - o2[0] != 0 ? o1[0] - o2[0] : o2[1] - o1[1]);
        int len = envelopes.length, maxLen = 1;
        // System.out.println(envelopes[len - 1][1]);
        // lastLength 数组的 i 位置代表 maxLen=i-1 的最长递增子序列的末尾数字
        int[] lastLength = new int[len];
        lastLength[0] = envelopes[0][1];
        int target = 0;
        for (int i = 1; i < len; i++) {
            if ((target = envelopes[i][1]) > lastLength[maxLen - 1]) { // 大于的时候添加
                lastLength[maxLen] = target;
                maxLen++;
            } else if (target == lastLength[maxLen - 1]) { // 等于时，跳过
                continue;
            } else { // 小于时，更新: 先二分找到第一个大于 target 的位置，然后替换为 target
                int left = 0, right = maxLen - 1;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (lastLength[mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                lastLength[left] = target;
            }
        }

        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
