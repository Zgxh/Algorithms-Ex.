/**
 * 剑指offer第49题：把字符串转换成整数
 *
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
 * 数值为0或者字符串不是一个合法的数值则返回0
 *
 * @author Yu Yang
 * @create 2020-02-24 12:04
 */
public class Question_49 {

    /**
     * 思路：先取第一位符号位，然后从低位开始转数字。注意结果用long存，防止负数转成正数的时候发生int越界。
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        if (str == null || str.length() == 0 || str == "0") {
            return 0;
        }
        boolean isNegative = false;
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {     // 获取正负号
            if (str.charAt(0) == '-') {
                isNegative = true;
            }
            str = str.substring(1);
        }
        long help = 1;
        long result = 0;        // 防止int越界
        for (int i = str.length() - 1; i >= 0; i--) {
            char curChar = str.charAt(i);
            if (curChar < '0' || curChar > '9') {
                return 0;
            }
            result += help * ((long)curChar - (long)'0');
            help *= 10;
        }
        result = isNegative ? -result : result;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {     // 判断是否数字越界，int范围为[-2147483648,2147483647]
            return 0;
        }
        return (int)result;
    }
}
