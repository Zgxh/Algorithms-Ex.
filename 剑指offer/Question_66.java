
/**
 * 剑指offer第66题：机器人的运动范围
 *
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下
 * 四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机
 * 器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），
 * 因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 *
 * @author Yu Yang
 * @create 2020-03-03 17:56
 */
public class Question_66 {

    /**
     * 思路：类似于65题，但比65简单。从（0,0）开始向下和右移动，递归进行，一旦大于k就停止。
     */

    int result = 0;
    boolean[][] visited = null;

    public int movingCount(int threshold, int rows, int cols) {
        visited = new boolean[rows][cols];
        movingCountHelp(threshold, rows, cols, 0, 0);
        return result;
    }

    public void movingCountHelp(int threshold, int rows, int cols, int i, int j) {
        if (sum(i) + sum(j) > threshold) {
            return;
        }
        result++;
        visited[i][j] = true;
        if (i < rows - 1 && visited[i + 1][j] == false) {
            movingCountHelp(threshold, rows, cols, i + 1, j);
        }
        if (j < cols - 1 && visited[i][j + 1] == false) {
            movingCountHelp(threshold, rows, cols, i, j + 1);
        }
    }

    public int sum(int x) {
        int ans = 0;
        while (x > 0) {
            ans += x % 10;
            x /= 10;
        }
        return ans;
    }
}
