/**
 * 剑指offer第52题：正则表达式匹配
 *
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有
 * 字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"
 * 和"ab*a"均不匹配
 *
 * @author Yu Yang
 * @create 2020-02-25 10:35
 */
public class Question_52 {

    /**
     * 思路：用递归的方法解决。根据模式串的下一个位置是否为'*'，分开讨论。下面的注释写的很明白。
     * @param str
     * @param pattern
     * @return
     */
    public boolean match(char[] str, char[] pattern) {
        return matchHelp(str, 0, pattern, 0);
    }

    public boolean matchHelp(char[] str, int i, char[] pattern, int j) {
        if (j == pattern.length) { // 递归退出条件，模式串到头了，对于字符串先到头的情况可以在下面退出
            if (i == str.length) {
                return true;
            }
            return false;
        }
        if (j + 1 < pattern.length && pattern[j + 1] == '*') { // 模式串下个位置是'*','*'可以匹配任意次数，包括0次
            if (i < str.length && (pattern[j] == '.' || pattern[j] == str[i])) { // 如果当前位置匹配上了,但也可能对应'*'匹配0次，str的i位置或许匹配pattern的j+2位置
                return matchHelp(str, i, pattern, j + 2) || matchHelp(str, i + 1, pattern, j);
            } else { // 没匹配上，只能判断str的i位置是否匹配pattern的j+2位置
                return matchHelp(str, i, pattern, j + 2);
            }
        } else {
            if (i < str.length && (pattern[j] == '.' || pattern[j] == str[i])) {
                return matchHelp(str, i + 1, pattern, j + 1);
            } else {
                return false;
            }
        }
    }
}
