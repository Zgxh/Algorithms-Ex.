/**
 * 剑指offer第53题：表示数值的字符串
 *
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 *
 * @author Yu Yang
 * @create 2020-02-25 10:35
 */
public class Question_53 {

    /**
     * 思路：没什么好办法，总结规则：
     * （1）字符串首可以有正负号，但字符串尾只能是数字
     * （2）e只能被使用一次，且前后都得有数字，e后面不能有小数点，但可以有正负号
     * （3）小数点只能使用一次，小数点也可以直接放在开头或开头的正负号后
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        if (str == null|| str.length == 0) {
            return false;
        }
        String string = String.valueOf(str);
        System.out.println(string);
        if (str[0] == '+' || str[0] == '-') {       // 首先排除开头正负号的限制
            string = string.substring(1);
        }
        // 首尾不能有非数字，除了小数点，针对".23"这样的情况
        if ((string.charAt(0) != '.' && (string.charAt(0) > '9' || string.charAt(0) < '0')) ||
                string.charAt(string.length() - 1) > '9' || string.charAt(string.length() - 1) < '0') {
            return false;
        }
        boolean e = true;       // 指示e还能否被使用
        boolean point = true;       // 指示小数点还能否被使用
        for (int i = 1; i < string.length() - 1; i++) {
            char curLoc = string.charAt(i);
            if ((curLoc > '9' || curLoc < '0') && (curLoc != '.') && (curLoc != 'e') && (curLoc != 'E')) {
                return false;
            } else if (curLoc == '.') {        // 如果是小数点，则只能出现在e之前，且只能被使用一次，小数点后只能是数字
                if (!point) {
                    return false;
                }
                if (string.charAt(i + 1) > '9' || string.charAt(i + 1) < '0') {
                    return false;
                }
                point = false;
            } else if (curLoc == 'e' || curLoc == 'E') {       // 如果是e，后面可以带正负号或数字
                if (!e) {
                    return false;
                }
                if (string.charAt(i + 1) == '+' || string.charAt(i + 1) == '-') {
                    i++;
                }
                e = false;
                point = false;
            }
        }
        return true;
    }
}
