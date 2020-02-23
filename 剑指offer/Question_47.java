/**
 * 剑指offer第47题：求1+2+...+n
 *
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * @author Yu Yang
 * @create 2020-02-23 11:45
 */
public class Question_47 {

    /**
     * 思路：利用逻辑与操作的短路特性。
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        int sum = n;
        boolean flag = (sum > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }
}
