//给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。 
//请注意，它是排序后的第k小元素，而不是第k个元素。 
//
// 示例: 
//
// 
//matrix = [
//   [ 1,  5,  9],
//   [10, 11, 13],
//   [12, 13, 15]
//],
//k = 8,
//
//返回 13。
// 
//
// 说明: 
//你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。 
// Related Topics 堆 二分查找


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：利用小顶堆，先把matrix第一行元素全放进去，之后每次弹出堆顶最小元素，并把弹出元素的下方元素入堆。
     * 进行k-1次后，堆顶元素就是第 k 小的元素。
     *
     * 之所以每次只放弹出元素的下方元素，是因为matrix的元素顺序从上到下、从左到右依次增大，其右边的元素一定大于右边元素上方的元素，
     * 而上方元素我们在第一次就已经放入堆了。
     *
     * 时间复杂度 O(k log n)
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {

        int iLen = matrix.length, jLen = matrix[0].length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        for (int j = 0; j < jLen; j++) {
            minHeap.offer(new int[]{0, j, matrix[0][j]});
        }
        while (!minHeap.isEmpty() && k > 1) {
            k--;
            int[] temp = minHeap.poll();
            if (temp[0] + 1 < iLen) {
                minHeap.offer(new int[]{temp[0] + 1, temp[1], matrix[temp[0] + 1][temp[1]]});
            }
        }
        return minHeap.peek()[2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
