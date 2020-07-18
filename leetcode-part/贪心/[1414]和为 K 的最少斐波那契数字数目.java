//给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。 
//
// 斐波那契数字定义为： 
//
// 
// F1 = 1 
// F2 = 1 
// Fn = Fn-1 + Fn-2 ， 其中 n > 2 。 
// 
//
// 数据保证对于给定的 k ，一定能找到可行解。 
//
// 
//
// 示例 1： 
//
// 输入：k = 7
//输出：2 
//解释：斐波那契数字为：1，1，2，3，5，8，13，……
//对于 k = 7 ，我们可以得到 2 + 5 = 7 。 
//
// 示例 2： 
//
// 输入：k = 10
//输出：2 
//解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
// 
//
// 示例 3： 
//
// 输入：k = 19
//输出：3 
//解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= 10^9 
// 
// Related Topics 贪心算法 数组 
// 👍 10 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：贪心算法。
     *
     * @param k
     * @return
     */
    public int findMinFibonacciNumbers(int k) {
        int count = 0;
        int sum = 0;
        LinkedList<Integer> stack = new LinkedList <>(); // 构造数组存放斐波那契数
        stack.addFirst(1);
        int lastTwo = 1;
        int last = 1;
        int item = 0;
        while ((item = last + lastTwo) <= k) {
            stack.addFirst(item);
            lastTwo = last;
            last = item;
        }
        // 贪心算法依次寻找当前可以放置的最大的数字
        while (sum < k) {
            if (stack.getFirst() > k - sum) {
                stack.removeFirst();
            } else {
                count++;
                sum += stack.getFirst();
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
