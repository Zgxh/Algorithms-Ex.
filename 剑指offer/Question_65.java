/**
 * 剑指offer第65题：矩阵中的路径
 *
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下
 * 移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 *
 * @author Yu Yang
 * @create 2020-03-02 19:46
 */
public class Question_65 {

    /**
     * 思路：根据题目要求，找路径，每当一个位置匹配的时候，可能此时是正确的路径，
     * 也可能不是，需要回退然后走别的路径。这里采用了递归的方式进行寻找，遍历所有的
     * 可能情况。具体见下面的代码。
     */

    private boolean[] visited = null; // 记录每个结点是否已经用过，初始值为false

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || matrix.length == 0 || str.length > matrix.length) {
            return false;
        }
        visited = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathHelp(matrix, rows, cols, i, j, 0, str)) { // 因为不止一个可能的起点，每一个可能的起点都要试一次
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPathHelp(char[] matrix, int rows, int cols, int i, int j, int index, char[] str) {
        if (matrix[i * cols + j] != str[index] || visited[i * cols + j] == true) {
            return false;
        }
        visited[i * cols + j] = true;
        if (index == str.length - 1) { // 最后一个匹配上了，则结束
            return true;
        }
        if (i > 0 && hasPathHelp(matrix, rows, cols, i - 1, j, index + 1, str)) {
            return true;
        }
        if (i < rows - 1 && hasPathHelp(matrix, rows, cols, i + 1, j, index + 1, str)) {
            return true;
        }
        if (j > 0 && hasPathHelp(matrix, rows, cols, i, j - 1, index + 1, str)) {
            return true;
        }
        if (j < cols - 1 && hasPathHelp(matrix, rows, cols, i, j + 1, index + 1, str)) {
            return true;
        }
        visited[i * cols + j] = false; // 如果当前字符匹配上了，但是下一个字符没匹配上，就要回退当前字符，继续寻找其他可能的情况
        return false;
    }
}
