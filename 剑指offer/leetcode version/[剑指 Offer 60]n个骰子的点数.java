//把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。 
//
// 
//
// 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。 
//
// 
//
// 示例 1: 
//
// 输入: 1
//输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
// 
//
// 示例 2: 
//
// 输入: 2
//输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0
//.05556,0.02778] 
//
// 
//
// 限制： 
//
// 1 <= n <= 11 
// 👍 117 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 动态规划，利用n-1对应的数组与n对应的数组之间的关系
    public double[] twoSum(int n) {
        double[] previousN = {1/6d, 1/6d, 1/6d, 1/6d, 1/6d, 1/6d};
        for (int i = 2; i <= n; i++) {
            double[] curN = new double[6 * i - i + 1];
            int preSize = previousN.length;
            for (int k = 0; k < preSize; k++) {
                for (int j = 0; j < 6; j++) {
                    curN[k + j] += previousN[k] * 1/6d;
                }
            }
            previousN = curN;
        }

        return previousN;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
