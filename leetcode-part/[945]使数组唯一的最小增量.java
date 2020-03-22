//给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。 
//
// 返回使 A 中的每个值都是唯一的最少操作次数。 
//
// 示例 1: 
//
// 输入：[1,2,2]
//输出：1
//解释：经过一次 move 操作，数组将变为 [1, 2, 3]。 
//
// 示例 2: 
//
// 输入：[3,2,1,2,1,7]
//输出：6
//解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
//可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
// 
//
// 提示： 
//
// 
// 0 <= A.length <= 40000 
// 0 <= A[i] < 40000 
// 
// Related Topics 数组


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：先明确：无论对谁加 1，最后得到的数字总和是一样的。期间插值时用到了等差数列求和。
     * 1. 先排序，然后遍历排序后的数组。
     * 2. 每当遇到相等的数字块，统计其总个数，并记录总和。
     * 3. 当遇到前后值不同的数字，说明两个数之间有缝隙可以中间插值，那就消灭可以被插到这里的相同元素。
     * 4. 最后遍历完数组后，如果还有没被消灭的元素，那就只能插到最大值后面。
     *
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {

        if (A == null || A.length == 0) {
            return 0;
        }

        Arrays.sort(A);
        int same = 0, result = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] == A[i]) {
                same++;
                result -= A[i];
            } else {
                int thisRemove = Math.min(same, A[i] - A[i - 1] - 1); // A[i-1] != A[i]了，本次可以消灭thisRemove个相同元素
                result += thisRemove * A[i - 1] + thisRemove * (1 + thisRemove) / 2; // 把当前还能被消灭的相同元素填补到A[i-1]与A[i]之间,等差数列求和
                same -= thisRemove;
            }
        }
        result += same * A[A.length - 1] + same * (1 + same) / 2;

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
