import java.util.Arrays;

/**
 * 剑指offer第28题：数组中出现次数超过一半的数字
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
 * 超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * @author Yu Yang
 * @create 2020-02-05 17:01
 */
public class Question_28 {

    /**
     * 思路1：先排序，取中位数，只有这个数可能满足条件，然后遍历一遍数组看是否满足。
     * 复杂度：当n够大时，O(nlogn)
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        Arrays.sort(array);
        int result = array[(array.length + 1) / 2 - 1];
        int times = 0;
        for (int num : array) {
            if (num == result) {
                times++;
            }
        }
        return times > array.length / 2 ? result : 0;
    }

    /**
     * 在题解讨论里看到一个比较技巧性的方法，时间复杂度O(n)。
     * 思路2：用preValue记录上一次访问的值，count表明当前值出现的次数，
     * 如果下一个值和当前值相同那么count++；如果不同count--，减到0的时候就要更换新的preValue值了，
     * 因为如果存在超过数组长度一半的值，那么最后preValue一定会是该值。
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution1(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int lastResult = array[0]; // 记录上一个可能的结果
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == lastResult) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    lastResult = array[i];
                    count = 1;
                }
            }
        }
        count = 0;
        for (int num : array) {
            if (num == lastResult) {
                count++;
            }
        }
        return count > array.length / 2 ? lastResult : 0;
    }
}
