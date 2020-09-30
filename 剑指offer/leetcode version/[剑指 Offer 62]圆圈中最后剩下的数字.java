//0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。 
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。 
//
// 
//
// 示例 1： 
//
// 输入: n = 5, m = 3
//输出: 3
// 
//
// 示例 2： 
//
// 输入: n = 10, m = 17
//输出: 2
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10^5 
// 1 <= m <= 10^6 
// 
// 👍 227 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 约瑟夫环问题。采用递归。
     * 第n个状态与第n-1个状态之间的传递关系：
     * n个数时，第一个被删掉的数的index是(m-1)%n，之后剩下n-1个数
     * 把下一个数看成是n-1个数的第一个数，则可以找到状态n对应的最后
     * 一个被删除的数字
     *
     */
    public int lastRemaining(int n, int m) {
        return recursion(n, m);
    }

    private int recursion(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int pre = recursion(n - 1, m);

        // m是从1开始的，n则是从0开始的
        return (m % n + pre) % n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
