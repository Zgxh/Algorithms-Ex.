//根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。 
//
// 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2
//, 1, 1, 0, 0]。 
//
// 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。 
// Related Topics 栈 哈希表


import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 思路：借助递减栈，遍历数组T：
     * 1）如果这天的温度小于栈顶的温度，这天入栈；
     * 2）如果这天的温度大于栈顶的温度，那么栈顶那天的时间跨度就是当前时间减掉栈顶时间，
     *    然后栈顶出栈，持续判断直到栈空或者满足递减栈入栈规则。
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] span = new int[T.length];
        List<Integer> stack = new LinkedList<>(); // 递减栈，存放递减温度对应的日期
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.get(stack.size() - 1)]) {
                int index = stack.get(stack.size() - 1);
                span[index] = i - index;
                stack.remove(stack.size() - 1);
            }
            stack.add(i);
        }
        return span;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
