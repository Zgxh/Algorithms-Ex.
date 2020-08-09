//给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。 
//
// 由于答案可能很大，因此返回答案模 10^9 + 7。 
//
// 
//
// 示例： 
//
// 输入：[3,1,2,4]
//输出：17
//解释：
//子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。 
//最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。 
//
// 
//
// 提示： 
//
// 
// 1 <= A <= 30000 
// 1 <= A[i] <= 30000 
// 
//
// 
// Related Topics 栈 数组 
// 👍 149 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路2：前驱、后继数组法
     *
     * 此思路从某个固定的最小值出发：对A[]中的每个数进行遍历，以此为最小值，统计以该值为最小值
     * 的所有子数组的个数。
     * 同样利用单调栈，分别从0和len-1出发，分别找到以i为min值的可能的子数组的最左边界和最右边界。
     * 然后 result += (i - pre[i]) * (next[i] - i) * A[i] 直接统计结果。
     */
    public int sumSubarrayMins(int[] A) {
        int len = A.length;
        // 前驱index
        int[] pre = new int[len];
        Deque<Integer> stack = new LinkedList();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
                stack.pop();
            }
            pre[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        // 后继index
        int[] next = new int[len];
        stack = new LinkedList();
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
                stack.pop();
            }
            next[i] = stack.isEmpty() ? len : stack.peek();
            stack.push(i);
        }
        int result = 0;
        int mod = 1_000_000_007;
        for (int i = 0; i < len; i++) {
            result += (i - pre[i]) * (next[i] - i) % mod * A[i] % mod;
            result %= mod;
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
