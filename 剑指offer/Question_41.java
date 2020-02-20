import java.util.ArrayList;

/**
 * 剑指offer第41题：和为S的连续正数序列
 *
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 *
 * @author Yu Yang
 * @create 2020-02-19 19:56
 */
public class Question_41 {

    /**
     * 思路：用两个标志代表序列的头和尾，从1和2开始，序列和比sum大了，就把最小的数去掉；
     * 序列和比sum小了，就把尾再往大进一个。停止的标志是小数大于序列和的一半，因为序列至少2个数。
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        if (sum < 3) {
            return new ArrayList<>();
        }
        int small = 1, big = 2;     // 记录序列的上下界
        int stopFlag = (sum + 1) / 2;       // 停止标志
        int curSum = 3;     // 记录当前序列的和
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        while (small < stopFlag) {
            if (curSum == sum) {
                ArrayList<Integer> curSequence = new ArrayList<>();
                for (int i = small; i <= big; i++) {
                    curSequence.add(i);
                }
                result.add(curSequence);
                big++;
                curSum = curSum + big - small;
                small++;
            } else if (curSum > sum) {
                curSum -= small;
                small++;
            } else {
                big++;
                curSum += big;
            }
        }
        return result;
    }
}
