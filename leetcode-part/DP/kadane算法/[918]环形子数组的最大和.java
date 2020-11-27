//给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。 
//
// 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 
//C[i+A.length] = C[i]） 
//
// 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, 
//k2 <= j 其中 k1 % A.length = k2 % A.length） 
//
// 
//
// 示例 1： 
//
// 输入：[1,-2,3,-2]
//输出：3
//解释：从子数组 [3] 得到最大和 3
// 
//
// 示例 2： 
//
// 输入：[5,-3,5]
//输出：10
//解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
// 
//
// 示例 3： 
//
// 输入：[3,-1,2,-1]
//输出：4
//解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
// 
//
// 示例 4： 
//
// 输入：[3,-2,2,-3]
//输出：3
//解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
// 
//
// 示例 5： 
//
// 输入：[-2,-3,-1]
//输出：-1
//解释：从子数组 [-1] 得到最大和 -1
// 
//
// 
//
// 提示： 
//
// 
// -30000 <= A[i] <= 30000 
// 1 <= A.length <= 30000 
// 
// Related Topics 数组 
// 👍 120 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 分成单区间子段和双区间子段：
    // 1. 单区间子段：直接对 0~len-1 求最大连续和
    // 2. 双区间子段：环形数组的末尾元素会连接开头元素。从中间断开，这样开头和结尾各引导一块数组。
    //         第一区间：以0位置开始，i位置结束；
    //         第二区间：以j位置开始，以len-1位置结束。然后加和。
    public int maxSubarraySumCircular(int[] A) {
        int len = A.length;
        if (len == 0) {
            return 0;
        }
        // 求所有元素的和
        int sum = 0;
        for (int num : A) {
            sum += num;
        }
        // 利用 kadane 算法计算单区间的最大连续和。
        // 其中双区间的情况转化为单区间的情况：
        // 先把每个元素变成负的，然后计算最大连续和，对应正数时就是最小连续和；然后 +sum 代表剩余的首尾两段相连是最大连续和
        int single = kadane(A, 0, len - 1, 1);
        int double1 = sum + kadane(A, 0, len - 2, -1);
        int double2 = sum + kadane(A, 1, len - 1, -1);

        return Math.max(single, Math.max(double1, double2));
    }

    // kadane 算法求解一个单区间的最大连续和。本质就是利用 DP 计算，优化成了 O(1) 的形式。
    // 这里对 kadane 算法进行了改动，加了一个符号位：
    // sign 表示该区间 A 内的元素是否需要改变符号。 1 代表不变号，-1 变号。
    private int kadane(int[] A, int left, int right, int sign) {
        if (left > right) {
            return 0;
        }
        int curMax = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            curMax = sign * A[i] + Math.max(curMax, 0);
            max = Math.max(max, curMax);
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
