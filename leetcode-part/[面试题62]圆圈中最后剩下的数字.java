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
//


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 直接用线性表模拟。
     * 也可以转化为约瑟夫环数学问题。
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {

        List<Integer> list = new ArrayList<>();
        int lastRemove = 0;

        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        while (list.size() > 1) {
            int curRemove = (lastRemove + m - 1) % list.size();
            list.remove(curRemove);
            lastRemove = curRemove;
        }

        return list.get(0);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
