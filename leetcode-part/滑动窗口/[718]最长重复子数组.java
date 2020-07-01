//给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。 
//
// 示例 1: 
//
// 
//输入:
//A: [1,2,3,2,1]
//B: [3,2,1,4,7]
//输出: 3
//解释: 
//长度最长的公共子数组是 [3, 2, 1]。
// 
//
// 说明: 
//
// 
// 1 <= len(A), len(B) <= 1000 
// 0 <= A[i], B[i] < 100 
// 
// Related Topics 数组 哈希表 二分查找 动态规划



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：滑动窗口.
     * A与B相对行走，然后每次错位一步，每次统计当前重叠部分内的maxLen。
     * 图示：
     * (1) A从中间开始，B从0开始
     *         <-    AAAAAAAAAAAAAAAA
     *                  BBBBBBBBBBBBBBBBB  ->
     * (2) A从0开始，B从中间开始
     *                  AAAAAAAAAAAAAAAAAAA     ->
     *          <-   BBBBBBBBBBBBBBBBB
     */
    private int[] A;
    private int[] B;

    public int findLength(int[] A, int[] B) {
        int lenA = A.length, lenB = B.length;
        this.A = A;
        this.B = B;
        int maxLen = 0;
        // (1) A从中间开始，B从0开始
        for (int i = 0; i < lenA; i++) {
            int lapLen = Math.min(lenB, lenA - i);
            int curMaxLen = curMaxLen(lapLen, i, 0);
            maxLen = Math.max(curMaxLen, maxLen);
        }
        // (2) A从0开始，B从中间开始
        for (int i = 0; i < lenB; i++) {
            int lapLen = Math.min(lenA, lenB - i);
            int curMaxLen = curMaxLen(lapLen, 0, i);
            maxLen = Math.max(curMaxLen, maxLen);
        }

        return maxLen;
    }

    /**
     * 统计当前重叠部分的最长相同子数组的长度。
     */
    private int curMaxLen(int lapLen, int locA, int locB) {
        int maxLen = 0, curLen = 0;
        for (int i = locA, j = locB; i < locA + lapLen && j < locB + lapLen; i++, j++) {
            if (A[locA] == B[locB]) {
                curLen++;
            } else {
                curLen = 0;
            }
            maxLen = Math.max(maxLen, curLen);
        }

        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
