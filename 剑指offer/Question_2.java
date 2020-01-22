/**
 * 剑指offer第二题：替换空格
 *
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy. 则经过替换之后的字符串为We%20Are%20Happy。
 *
 * @author Yu Yang
 * @create 2020-01-22 21:10
 */
public class Question_2 {

    /**
     * 可以通过java语句直接实现，其源码使用了正则表达式。str.toString().replaceAll("\s", "%20")
     *
     * 思路：看代码。。。。
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                str.setCharAt(i, '%');
                str.insert(i + 1, "20");
            }
        }
        return str.toString();
    }
}
