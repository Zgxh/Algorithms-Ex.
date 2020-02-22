import java.util.LinkedList;

/**
 * 剑指offer第46题：圆圈中最后剩下的数
 *
 * 0,1,...,n-1这n个数字排成一个圆圈，从数字0开始每次从这个圆圈中里删除第m个数字。
 * 求这个圆圈里剩下的最后一个数字。
 * （如果没有数字，返回-1）
 *
 * @author Yu Yang
 * @create 2020-02-22 15:52
 */
public class Question_46 {

    /**
     * 思路1：用链表模拟这个过程。
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution_1(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        LinkedList<Integer> list = new LinkedList<>();
        int lastRemove = 0;
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        while (list.size() > 1) {
            int curRemove = (lastRemove + m) % n - 1;
            if (curRemove == -1) {
                curRemove = list.size() - 1;
            }
            list.remove(curRemove);
            lastRemove = curRemove;
            n--;
        }
        return list.get(0);
    }

    /**
     * 思路2：数学分析法。原序列在第一次删除k后得到0,1,...,k-1,k+1,...,n-1。下一次从k+1开始。
     * 因此第2次的序列可以看成k+1,...,n-1,0,1,...,k-1。
     * 即删除k后新序列的index映射为：(index-(k+1)) / (n-1)，
     * 倒过来，按照递归的返回次序，从m次删除到上一次（m-1次删除）的关系为：(index+(k+1)) / n
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        return n == 1 ? 0 : (LastRemaining_Solution(n - 1, m) + m) % n;
    }
}
