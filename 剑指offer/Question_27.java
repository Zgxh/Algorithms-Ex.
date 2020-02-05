import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 剑指offer第27题：字符串的排列
 *
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * （输入字符串长度不超过9(可能有字符重复),字符只包括大小写字母。）
 *
 * @author Yu Yang
 * @create 2020-02-05 12:53
 */
public class Question_27 {

    /**
     * 字符的全排列。牛客的测评系统要求结果要有序，所以本来想用StringBuilder测评未通过,所以改用了String。
     * 思路：长字符串，先确定第一个字符，然后递归排列剩下的子字符串，代码如下。
     * 注意：输入的字符串可能有重复的字符，所以要用一个set存一下已经交换过的字符。
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        ArrayList<String> result = new ArrayList<>();
        if (str.length() == 1) {
            result.add(str);
        } else {
            TreeSet<Character> swapped = new TreeSet<>(); // 存放已经交换过的字符
            for (int i = 0; i < str.length(); i++) {
                String copy = new String(str);
                if (swapped.contains(copy.charAt(i))) { // 应对重复字符的情况
                    continue;
                }
                if (i > 0) {
                    swapped.add(copy.charAt(i));
                    copy = copy.charAt(i) + copy.substring(0, i) + copy.substring(i+1); // 尽量保持原有字符顺序...
                }
                ArrayList<String> strings = Permutation(copy.substring(1));
                for (String str1 : strings) {
                    result.add(copy.charAt(0) + str1);
                }
            }
        }
        return result;
    }
}
