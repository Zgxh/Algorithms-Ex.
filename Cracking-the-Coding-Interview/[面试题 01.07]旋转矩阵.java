//给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。 
//
// 不占用额外内存空间能否做到？ 
//
// 
//
// 示例 1: 
//
// 给定 matrix = 
//[
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
//],
//
//原地旋转输入矩阵，使其变为:
//[
//  [7,4,1],
//  [8,5,2],
//  [9,6,3]
//]
// 
//
// 示例 2: 
//
// 给定 matrix =
//[
//  [ 5, 1, 9,11],
//  [ 2, 4, 8,10],
//  [13, 3, 6, 7],
//  [15,14,12,16]
//], 
//
//原地旋转输入矩阵，使其变为:
//[
//  [15,13, 2, 5],
//  [14, 3, 4, 1],
//  [12, 6, 8, 9],
//  [16, 7,10,11]
//]
// 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 矩阵是个方阵，从外层向内层一圈一圈旋转，关键在于找对应边坐标之间的关系，逻辑很简单。
     * 一的x和二的y有关系，一的y与二的x有关系。这个关系要么就是相等，要么就是加起来等于len-1.
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {

        int len = matrix.length;

        for (int level = 0; level < len / 2; level++) { // 控制当前旋转哪一层
            for (int i = level; i < len - 1 - level; i++) { // 控制当前层的开始点的y坐标
                // 矩形四条边4个对应位置的元素依次覆盖
                int temp = matrix[level][i];
                matrix[level][i] = matrix[len - 1 - i][level]; // 一的x和二的y有关系，一的y与二的x有关系
                matrix[len - 1 - i][level] = matrix[len - 1 - level][len - 1 - i];
                matrix[len - 1 - level][len - 1 - i] = matrix[i][len - 1 - level];
                matrix[i][len - 1 - level] = temp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
