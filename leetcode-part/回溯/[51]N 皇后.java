//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例： 
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// Related Topics 回溯算法 
// 👍 564 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 回溯法。
     *
     * 注意点：
     * 1.使用了含有n-1个'.'字符的字符串模板，方便之后插入皇后结点
     * 2.利用三个集合来记录不能放置的位置
     *      - 正下方
     *      - 左对角
     *      - 右对角
     *   其中左、右对象分别减row来归置到row=0的情况上，然后每次判断再 +row 偏置到目标行
     *   集合可以被位运算来取代，即利用int型变量，通过对对应位置置1和左右移位，
     *   来替代集合的作用。
     */
    private List<List<String>> result;
    private int n;
    private StringBuilder template;
    private boolean[] visited;

    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList();
        this.n = n;
        visited = new boolean[n];
        template = new StringBuilder(); // 字符串模板，包含n-1个'.'
        for (int i = 0; i < n - 1; i++) {
            template.append('.');
        }
        solveHelp(new ArrayList(), 0, new HashSet(), new HashSet());

        return result;
    }

    private void solveHelp(List<String> list, int row, Set<Integer> left, Set<Integer> right) {
        if (row == n - 1) {
            for (int i = 0; i < n; i++) {
                if (!visited[i] && !left.contains(i + row) && !right.contains(i - row)) {
                    StringBuilder sb = new StringBuilder(template);
                    sb.insert(i, 'Q');
                    list.add(sb.toString());
                    result.add(new ArrayList(list));
                    list.remove(list.size() - 1);
                }
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && !left.contains(i + row) && !right.contains(i - row)) {
                StringBuilder sb = new StringBuilder(template);
                sb.insert(i, 'Q');
                visited[i] = true;
                list.add(sb.toString());
                left.add(i - 1 + (row + 1)); // 消除 row 的影响，全归置到 row=0 的行
                right.add(i + 1 - (row + 1));
                solveHelp(list, row + 1, left, right);
                visited[i] = false;
                list.remove(list.size() - 1);
                left.remove(i - 1 + (row + 1));
                right.remove(i + 1 - (row + 1));
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
