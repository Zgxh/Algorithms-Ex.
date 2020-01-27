/**
 * 剑指offer第11题：二进制中1的个数
 *
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 * 补充：负数的补码是其源码除了符号位，所有位取反，再加 1。
 * 在机器中，整数的储存和计算都是补码。
 *
 * @author Yu Yang
 * @create 2020-01-27 19:40
 */
public class Question_11 {

    /**
     * 思路：借助flag=1，利用位运算，每次左移一位，算出所有的1的位数。
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        int result = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                result++;
            }
            flag <<= 1;
        }
        return result;
    }

    /**
     * 一种比较骚的操作：整数n，进行n&(n-1)运算，会把二进制表示中最右边的1变为0。
     * 一直进行这个操作，直到n变成0，统计操作进行的次数。
     */
    //    public int NumberOf1(int n) {
    //        int result = 0;
    //        while (n != 0) {
    //            result++;
    //            n = (n - 1) & n;
    //        }
    //        return result;
    //    }
}
