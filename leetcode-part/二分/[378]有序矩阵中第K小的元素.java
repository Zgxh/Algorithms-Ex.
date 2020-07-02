//给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。 
//请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例： 
//
// matrix = [
//   [ 1,  5,  9],
//   [10, 11, 13],
//   [12, 13, 15]
//],
//k = 8,
//
//返回 13。
// 
//
// 
//
// 提示： 
//你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。 
// Related Topics 堆 二分查找


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路1：利用小顶堆。
     *      首先把第一行全加入堆，然后弹出最小的元素，再加入其下方元素；重复这个过程，直至找到第k小的元素。
     * 思路2：二分查找。
     *      二分查找某个目标数字，然后对每个可能的目标数字进行判断：是否小于他的数字为k个，然后进一步二分范围。
     *      对每次判断，从matrix的左下角开始，如果数字小于mid，则右移，并加上当前列所有小于等于mid的数字个数；
     *      如果大于mid，则上移。
     */

    int[][] matrix;
    int n;
    int k;

    public int kthSmallest(int[][] matrix, int k) {
        this.matrix = matrix;
        n = matrix.length;
        this.k = k;

        // 二分查找
        int left = matrix[0][0], right = matrix[n-1][n-1];
        while (left < right) {
            int mid = (left + right) >> 1;
            if (largeRight(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean largeRight(int mid) {
        int i = n - 1, j = 0; // 从左下角出发
        int count = 0; // 记录小于等于mid的数字的个数

        while (i >= 0 && j <= n - 1) {
            if (matrix[i][j] <= mid) { // 向右走，并加上上一列所有小于mid的总个数
                count += i + 1;
                j++;
            } else { // 向上走
                i--;
            }
        }

        return count >= k; // 保证最后收敛时的数一定在matrix中
    }
}
//leetcode submit region end(Prohibit modification and deletion)
