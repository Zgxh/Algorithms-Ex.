//给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。 
//
//
// 如果有多个目标子集，返回其中任何一个均可。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2] (当然, [1,3] 也正确)
// 
//
// 示例 2: 
//
// 输入: [1,2,4,8]
//输出: [1,2,4,8]
// 
// Related Topics 数学 动态规划 
// 👍 175 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DP。
    // 基于一个推论：一个递增的整除子集，如果新来一个元素大于该子集的最大元素，
    // 且整除最大元素，则这个新来的元素可以加入到该子集中
    // 时间复杂度 O(n ^ 2)
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList();
        }

        Arrays.sort(nums); // 从小到大排序
        int[] dp = new int[len]; // dp[i]: 以nums[i]结尾的最大整除子集的长度
        int maxSubsetLen = -1, maxSubsetIndex = -1; // 记录最大子集的长度，和最大子集的最后一个数字

        // 对于每个nums[i]，遍历i位置之前的所有元素，寻找最大子集
        for (int i = 0; i < len; i++) {
            for (int k = 0; k < i; k++) {
                if (nums[i] % nums[k] == 0 && dp[k] >= dp[i]) {
                    dp[i] = dp[k] + 1;
                }
            }
            if (dp[i] > maxSubsetLen) {
                maxSubsetLen = dp[i];
                maxSubsetIndex = i;
            }
        }
        // 利用 dp 数组和子集长度来恢复最大整除子集的元素
        List<Integer> list = new ArrayList(maxSubsetLen);
        int tempIndex = maxSubsetIndex;
        int tempLen = maxSubsetLen;
        for (int i = maxSubsetIndex; i >= 0; i--) {
            if (tempLen < 0) {
                break;
            }
            if (nums[tempIndex] % nums[i] == 0 && dp[i] == tempLen) {
                tempLen--;
                list.add(nums[i]);
                tempIndex = i;
            }
        }
        Collections.reverse(list);

        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
