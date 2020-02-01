import java.util.ArrayList;

/**
 * 剑指offer第19题：顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵：
 * 1 2 3 4
 * 5 6 7 8
 * 9 10 11 12
 * 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * @author Yu Yang
 * @create 2020-01-31 21:12
 */
public class Question_19 {

    /**
     * 这题没意思，按print的顺序一层层收缩边界。题解中也大致都是这么个意思，没有特别高明的方法。
     * 时间O(n)，空间O(1)
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int minRows = 0, minCols = 0;
        int maxRows = matrix.length - 1, maxCols = matrix[0].length - 1;
        int i = 0, j = 0;
        ArrayList<Integer> result = new ArrayList<>();
        while (minRows <= maxRows && minCols <= maxCols) {
            if (i <= maxRows && i>=minRows && j <= maxCols && j>=minCols) {
                while (j <= maxCols) { // 从左到右
                    result.add(matrix[i][j]);
                    j++;
                }
                j--;
                i++;
                minRows++;
            }
            if (j <= maxCols && j>=minCols && i <= maxRows && i >= minRows) {
                while (i <= maxRows) { // 从上到下
                    result.add(matrix[i][j]);
                    i++;
                }
                i--;
                j--;
                maxCols--;
            }
            if (i <= maxRows && i>=minRows && j <= maxCols && j>=minCols) {
                while (j >= minCols) { // 从右到左
                    result.add(matrix[i][j]);
                    j--;
                }
                j++;
                i--;
                maxRows--;
            }
            if (j <= maxCols && j>=minCols && i <= maxRows && i >= minRows) {
                while (i >= minRows) { // 从下到上
                    result.add(matrix[i][j]);
                    i--;
                }
                i++;
                j++;
                minCols++;
            }
        }
        return result;
    }
}
