//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回 n 皇后不同的解决方案的数量。 
//
// 示例: 
//
// 输入: 4
//输出: 2
//解释: 4 皇后问题存在如下两个不同的解法。
//[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N
//-1 步，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法 
// 👍 195 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 回溯法。
    // 设置 visited[] 数组判断当前列有没有被占用过
    // left， right 分别指示左斜边与右斜边，其中减去了row的影响

    private int result = 0;
    private int n;
    private boolean[] visited; // 指示当前竖列有没有被使用

    public int totalNQueens(int n) {
        this.n = n;
        visited = new boolean[n];
        solveHelp(0, new HashSet(), new HashSet());

        return result;
    }

    // 从第 row 行开始递归并回溯，left、right表示row行上面的元素已经占用的位置，已经消除了row的影响，全部归0化到了第一行
    // left : 集合中的点会向左下角延伸
    // right : 集合中的点会向右下角延伸
    private void solveHelp(int row, Set<Integer> left, Set<Integer> right) {
        if (row == n - 1) {
            for (int i = 0; i < n; i++) {
                if (!visited[i] && !left.contains(i + row) && !right.contains(i - row)) {
                    result++;
                }
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && !left.contains(i + row) && !right.contains(i - row)) {
                visited[i] = true;
                // 消除 row 的影响，全归置到 row=0 的行
                left.add(i - 1 + (row + 1));
                right.add(i + 1 - (row + 1));
                solveHelp(row + 1, left, right);
                visited[i] = false;
                left.remove(i - 1 + (row + 1));
                right.remove(i + 1 - (row + 1));
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
