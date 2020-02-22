import java.util.TreeSet;

/**
 * 剑指offer第45题：扑克牌顺子
 *
 * 从扑克牌中随机抽5张牌，判断是否是顺子。如果牌能组成顺子就输出true，否则就输出false。
 * 大小王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 为了方便起见,你可以认为大小王是0。
 *
 * @author Yu Yang
 * @create 2020-02-22 14:58
 */
public class Question_45 {

    /**
     * 思路：利用set的特点，存放数组中的数字。
     * （1）如果除了大小王外，其他数字重复，则set.size() + numOfZero一定不是5。
     * （2）treeset这里采用了默认比较器（数字自然顺序），可以方便地确定最大（last）、最小值（first）。
     * @param numbers
     * @return
     */
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length != 5) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>();
        int numOfZero = 0;      // 记录大小王出现的次数
        for (int num : numbers) {
            if (num == 0) {
                numOfZero++;
            } else {
                set.add(num);
            }
        }
        if ((set.size() + numOfZero == 5) && (set.last() - set.first() < 5)) {
            return true;
        }
        return false;
    }
}
