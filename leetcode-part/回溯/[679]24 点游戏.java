//你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。 
//
// 示例 1: 
//
// 输入: [4, 1, 8, 7]
//输出: True
//解释: (8-4) * (7-1) = 24
// 
//
// 示例 2: 
//
// 输入: [1, 2, 1, 2]
//输出: False
// 
//
// 注意: 
//
// 
// 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。 
// 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允
//许的。 
// 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。 
// 
// Related Topics 深度优先搜索 
// 👍 119 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：回溯法。
     * 遍历所有的组合，每次取两个数进行四则运算，然后把结果跟剩余的数继续递归进行，
     * 然后回溯继续遍历其他情况。
     */
    private final double EPSILON = 1e-6;
    private final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList(4);
        for (int num : nums) {
            list.add((double) num);
        }

        return backtracking(list);
    }

    private boolean backtracking(List<Double> list) {
        int size = list.size();
        if (size == 1) {
            return Math.abs(list.get(0) - 24) < EPSILON;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) { // 从list中随机找两个数进行运算
                    continue;
                }
                List<Double> tempList = new ArrayList(); // 存放剩余的数与当前两个数的运算结果
                for (int k = 0; k < size; k++) { // 放入除了被运算的两个数外，剩余的数
                    if (k != i && k != j) {
                        tempList.add(list.get(k));
                    }
                }
                for (int k = 0; k < 4; k++) {
                    if (k < 2 && i > j) { // 加法和乘法满足交换率，避免重复计算
                        continue;
                    }
                    double iValue = list.get(i), jValue = list.get(j);
                    if (k == ADD) {
                        tempList.add(iValue + jValue);
                    } else if (k == MULTIPLY) {
                        tempList.add(iValue * jValue);
                    } else if (k == SUBTRACT) {
                        tempList.add(iValue - jValue);
                    } else { // 除法
                        if (Math.abs(jValue) < EPSILON) { // 避免除零
                            continue;
                        }
                        tempList.add(iValue / jValue);
                    }
                    if (backtracking(tempList)) {
                        return true;
                    }
                    tempList.remove(tempList.size() - 1);
                }
            }
        }

        return false; // 遍历完所有情况，没找到合适的组合
    }
}
//leetcode submit region end(Prohibit modification and deletion)
