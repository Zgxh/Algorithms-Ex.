/**
 * 剑指offer第48题：不用加减乘除做加法
 *
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 *
 * @author Yu Yang
 * @create 2020-02-24 12:04
 */
public class Question_48 {

    /**
     * 思路：这种题肯定是用位运算来做。分析异或的特点，用位异或实现不带进位的加和，然后用位与和位左移实现进位。
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return Add(num1 ^ num2, (num1 & num2) << 1);
    }
}
