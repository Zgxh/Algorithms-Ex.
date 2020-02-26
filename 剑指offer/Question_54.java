import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer第54题：字符流中第一个不重复的字符
 *
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 *
 * @author Yu Yang
 * @create 2020-02-26 16:43
 */
public class Question_54 {

    /**
     * 思路：使用队列存放字符的出现次序，按顺序存放出现过的char，用int[128]存放每个字符出现的次数；
     * 每当要使用第一个不重复的char时，判断队首元素是否只出现一次，否则弹出。
     */
    int[] countChar = new int[128];
    Queue<Character> queue = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch)
    {
        if (countChar[ch] == 0) {
            queue.add(ch);
        }
        countChar[ch]++;
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        while (!queue.isEmpty() && countChar[queue.peek()] > 1) {
            queue.poll();
        }
        if (queue.isEmpty()) {
            return '#';
        }
        return queue.peek();
    }
}
