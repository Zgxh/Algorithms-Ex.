/**
 * 剑指offer第34题：第一个只出现一次的字符
 *
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 *
 * @author Yu Yang
 * @create 2020-02-12 21:15
 */
public class Question_34 {

    /**
     * 思路1：遍历一遍字符串，记录下每个字符的出现次数，然后再遍历一遍记录者，找第一个只出现一次的。时间复杂度O(n)
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        int[] count = new int[256];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (count[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 思路2：借助java的API，直接判断每个字符是否还在别的地方出现过,比思路1慢。最坏应该是n^2级
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar2(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!(str.substring(0, i) + str.substring(i + 1)).contains(str.charAt(i) + "")) {
                return i;
            }
        }
        return -1;
    }
}
