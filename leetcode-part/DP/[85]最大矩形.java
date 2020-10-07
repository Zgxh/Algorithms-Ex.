//给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 示例: 
//
// 输入:
//[
//  ["1","0","1","0","0"],
//  ["1","0","1","1","1"],
//  ["1","1","1","1","1"],
//  ["1","0","0","1","0"]
//]
//输出: 6 
// Related Topics 栈 数组 哈希表 动态规划 
// 👍 618 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 维护dp[]数组，并按行更新。dp维护内容：
    // 第i个点向上所能达到的最大高度height、以该高度向左右最多能到达的位置left、right
    // 在按行更新的过程中，height遇到位置为0的点会变为0，同时left和right会往内收缩
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int iLen = matrix.length, jLen = matrix[0].length;
        int[] height = new int[jLen];
        // left 初始化所有位置都是0
        int[] left = new int[jLen];
        // right 初始化为所有位置都是 jLen - 1
        int[] right = new int[jLen];
        Arrays.fill(right, jLen - 1);
        int maxArea = 0;
        // 按行更新dp数组
        for (int i = 0; i < iLen; i++) {
            int curLeft = 0, curRight = jLen;
            // 更新 height 数组
            for (int j = 0; j < jLen; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            // 更新 left 数组
            for (int j = 0; j < jLen; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0; // 当前行，该位置高度为0，为下一行还原left值
                    curLeft = j + 1; // 接下来要遍历的点left会向右收缩
                }
            }
            // 更新 right 数组
            for (int j = jLen - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = jLen - 1; // 当前行，该位置高度为0，为下一行还原right值
                    curRight = j - 1; // 接下来要遍历的点right会向左收缩
                }
            }
            // 更新maxArea 最大面积
            for (int j = 0; j < jLen; j++) {
                maxArea = Math.max(maxArea, height[j] * (right[j] - left[j] + 1));
            }
        }

        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
