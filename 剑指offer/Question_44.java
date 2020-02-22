
/**
 * 剑指offer第44题：旋转单词顺序列
 *
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 *
 * @author Yu Yang
 * @create 2020-02-22 13:20
 */
public class Question_44 {

    /**
     * 思路：把原字符串str按空格分隔，然后倒序连接，中间加上空格。
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        String[] seg = str.split(" ");
        if (seg.length == 0) {
            return str;
        }
        String result = seg[seg.length - 1];
        for (int i = seg.length - 2; i >= 0; i--) {
            result = result + " " + seg[i];
        }
        return result;
    }
}
