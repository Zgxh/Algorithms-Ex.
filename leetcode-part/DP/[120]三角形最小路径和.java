//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。 
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 例如，给定三角形： 
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。 
//
// 
//
// 说明： 
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。 
// Related Topics 数组 动态规划


import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：动态规划。
     * 利用triangle本身的存储空间，层次地遍历双层list，每次更新当前层为总的路径，
     * 即从第一层到当前节点的最小距离，直到最后一层list也被遍历并更改完毕。
     * 最后统计最后一层的最小值。
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        if (len == 1) {
            return triangle.get(0).get(0);
        }
        for (int i = 1; i < len; i++) {
            List<Integer> last = triangle.get(i - 1);
            List<Integer> list = triangle.get(i);
            int len2 = list.size();
            for (int j = 0; j < len2; j++) {
                if (j == 0) {
                    list.set(j, last.get(0) + list.get(0));
                } else if (j == len2 - 1) {
                    list.set(j, last.get(j - 1) + list.get(len2 - 1));
                } else {
                    list.set(j, list.get(j) + Math.min(last.get(j - 1), last.get(j)));
                }
            }
        }
        int minPath = Integer.MAX_VALUE;
        for (int path : triangle.get(len - 1)) {
            minPath = Math.min(path, minPath);
        }
        return minPath;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
