//给定一个二维网格和一个单词，找出该单词是否存在于网格中。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 这题和剑指offer第65题一样，使用visited数组记录每个位置有没有被访问过。
     * 思路很简单，递归往下找。
     */

    boolean[][] visited;
    int iLen, jLen;

    public boolean exist(char[][] board, String word) {

        if (board == null || board.length == 0 ||
                board[0] == null || board[0].length == 0 ||
                word == null || word.length() == 0) {
            return false;
        }

        iLen = board.length;
        jLen = board[0].length;
        visited = new boolean[iLen][jLen];

        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (existHelp(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existHelp(char[][] board, int i, int j, String word, int index) {

        if (board[i][j] != word.charAt(index) || visited[i][j] == true) {
            return false;
        }
        visited[i][j] = true;
        if (index == word.length() - 1) { // 最后一个字符匹配上了，直接结束
            return true;
        }
        if (i > 0 && existHelp(board, i - 1, j, word, index + 1)) {
            return true;
        }
        if (i < iLen - 1 && existHelp(board, i + 1, j, word, index + 1)) {
            return true;
        }
        if (j > 0 && existHelp(board, i, j - 1, word, index + 1)) {
            return true;
        }
        if (j < jLen - 1 && existHelp(board, i, j + 1, word, index + 1)) {
            return true;
        }
        visited[i][j] = false; // 还原现场，这次不成，下种情况还有机会
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
