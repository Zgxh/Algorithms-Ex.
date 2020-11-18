//我们有两个长度相等且不为空的整型数组 A 和 B 。 
//
// 我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。 
//
// 在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... < A[A.lengt
//h - 1]）。 
//
// 给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。 
//
// 
//示例:
//输入: A = [1,3,5,4], B = [1,2,3,7]
//输出: 1
//解释: 
//交换 A[3] 和 B[3] 后，两个数组如下:
//A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
//两个数组均为严格递增的。 
//
// 注意: 
//
// 
// A, B 两个数组的长度总是相等的，且长度的范围为 [1, 1000]。 
// A[i], B[i] 均为 [0, 2000]区间内的整数。 
// 
// Related Topics 动态规划 
// 👍 132 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 循环不变式 问题。
    // 根据前一对元素交换，与当前对元素交换的依赖性来进行更新：
    // 1. 无依赖：前一对元素交换与否不影响当前元素对的交换与否；
    // 2. 一致依赖：前一对元素交换与否，和当前对元素交换与否，保持一致；
    // 3. 相反依赖：前一对元素交换与否，和当前对元素交换与否，正好相反。
    public int minSwap(int[] A, int[] B) {
        int noSwap = 0, swap = 1; // noSwap:前一对不交换； swap:前一对交换。
        int len = A.length;
        for (int i = 1; i < len; i++) {
            boolean case1 = A[i - 1] < A[i] && B[i - 1] < B[i];
            boolean case2 = A[i - 1] < B[i] && B[i - 1] < A[i];
            if (case1 && case2) { // 此时满足无依赖条件，换不换都随意，如果换就多一次交换
                noSwap = Math.min(noSwap, swap);
                swap = noSwap + 1;
            } else if (case1) { // 此时满足一致依赖
                // 要么前一对和当前对都交换，要么都不交换
                // noSwap = noSwap;
                swap = swap + 1;
            } else if (case2) { // 此时满足相反依赖
                int temp = noSwap;
                noSwap = swap; // 如果前一对换了，则当前对不能换
                swap = temp + 1; // 如果前一对没换，则当前对必须换
            }
        }

        return Math.min(noSwap, swap);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
