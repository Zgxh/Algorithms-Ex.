//一个已排序好的表 A，其包含 1 和其他一些素数. 当列表中的每一个 p<q 时，我们可以构造一个分数 p/q 。 
//
// 那么第 k 个最小的分数是多少呢? 以整数数组的形式返回你的答案, 这里 answer[0] = p 且 answer[1] = q. 
//
// 示例:
//输入: A = [1, 2, 3, 5], K = 3
//输出: [2, 5]
//解释:
//已构造好的分数,排序后如下所示:
//1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
//很明显第三个最小的分数是 2/5.
//
//输入: A = [1, 7], K = 1
//输出: [1, 7]
// 
//
// 注意: 
//
// 
// A 长度的取值范围在 2 — 2000. 
// 每个 A[i] 的值在 1 —30000. 
// K 取值范围为 1 —A.length * (A.length - 1) / 2 
// 
// Related Topics 堆 二分查找 
// 👍 52 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：二分
     * 对分数的值进行二分，初始边界是0，1
     *
     */

    private int[] A;
    private int len;

    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        this.A = A;
        this.len = A.length;
        double left = 0, right = 1; // 分数的范围一定在 (0, 1) 范围内
        while (right - left > 1e-9) {
            double mid = left + (right - left) / 2.0;
            int[] search = countLessThanMid(mid); // 查询比mid小的分数有多少个
            if (search[0] == K) {
                return new int[]{search[1], search[2]};
            } else if (search[0] < K) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return new int[]{A[0], A[1]}; // 无答案
    }

    private int[] countLessThanMid(double x) {
        int numerator = 0, denominator = 1;
        int i = -1; // 因为A是递增数组，所以i可以被共用：因为j增大的时候，i必然会只增不减
        int count = 0;
        for (int j = 0; j < len; j++) {
            while (i + 1 < j && A[i + 1] < x * A[j]) {
                i++;
            }
            count += i + 1;
            if (i >= 0 && A[i] * denominator > numerator * A[j]) { // 找到当前小于目标值的最大值，其对应的分子和分母
                numerator = A[i];
                denominator = A[j];
            }
        }

        return new int[]{count, numerator, denominator};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
