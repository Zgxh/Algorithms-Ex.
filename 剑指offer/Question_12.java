/**
 * 剑指offer第12题：数值的整数次方
 *
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 保证base和exponent不同时为0.
 *
 * @author Yu Yang
 * @create 2020-01-28 21:27
 */
public class Question_12 {

    /**
     * 通过二分的方式来优化。
     * 时间复杂度：O(log n)
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        boolean flag = false; // 判断exponent的正负
        if (exponent < 0) {
            exponent = -exponent;
            flag = true;
        }
        double result = getPower(base, exponent);
        if (flag) {
            return 1 / result;
        }
        return result;
    }

    /**
     * 用二分+递归求base的正数次幂
     * @param base
     * @param exponent
     * @return
     */
    public double getPower(double base, int exponent) {
        if (exponent == 1) {
            return base;
        }
        double temp = getPower(base, exponent >> 1);
        if (exponent % 2 == 1) {
            return temp * temp * base;
        }
        return temp * temp;
    }
}
