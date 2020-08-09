import java.util.Deque;
import java.util.LinkedList;

////给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
////
//// 由于答案可能很大，因此返回答案模 10^9 + 7。
////
////
////
//// 示例：
////
//// 输入：[3,1,2,4]
////输出：17
////解释：
////子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
////最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
////
////
////
//// 提示：
////
////
//// 1 <= A <= 30000
//// 1 <= A[i] <= 30000
////
////
////
//// Related Topics 栈 数组
//// 👍 136 👎 0
//
//
//import java.util.Stack;
//
////leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路1：单调栈。
     * 遍历所有组合的思路：以A[]数组中每个位置i的数字做结尾，其对应从0~i开始；
     * 所有的结尾遍历一遍，即为所有的子数组组合。
     * 按照结尾驱动的方式，通过结尾index进行遍历。
     * 维护一个单调栈，栈中的数据是递增的，在对结尾index进行遍历的过程中，依次
     * 加入更大的元素，表示开始的位置有多靠前，当前子数组的最小值才会发生变化。
     * 进而可以统计出每个结尾index对应的所有可能的子数组，所对应的最小值之和。
     * 前一个结尾index的最小值之和与后一个结尾index的最小值之和是有关联的，通过temp
     * 变量进行存储。
     *
     */
    private int MOD = 1_000_000_007;

    public int sumSubarrayMins(int[] A) {
        int len = A.length;
        Deque<Pair> stack = new LinkedList<>(); // 单调栈
        int result = 0, temp = 0;
        for (int i = 0; i < len; i++) {
            int count = 1; // 子数组以i结尾时，单调栈栈顶对应的min值一共会出现在几个子数组中
            while (!stack.isEmpty() && stack.peek().val >= A[i]) {
                Pair pop = stack.pop(); // 栈顶需要更新，因为i位置的值比栈顶还要小
                count += pop.count; // 把temp中冗余的部分删掉
                temp -= pop.val * pop.count;
            }
            stack.push(new Pair(A[i], count));
            temp += A[i] * count; // temp 更新为栈更新后的值
            result += temp;
            result %= MOD;
        }

        return result;
    }
}

class Pair {
    public int val;
    public int count; // 对子数组个数计数

    public Pair(int val, int count) {
        this.val = val;
        this.count = count;
    }
}



////leetcode submit region end(Prohibit modification and deletion)
