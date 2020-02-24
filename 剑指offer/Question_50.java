import java.util.HashSet;
import java.util.Set;

/**
 * 剑指offer第50题：数组中重复的数字
 *
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，
 * 但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个
 * 重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出
 * 是第一个重复的数字2。
 *
 * @author Yu Yang
 * @create 2020-02-24 12:04
 */
public class Question_50 {

    /**
     * 思路1：用hashset解决，时间、空间复杂度O(n)
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int[] numbers, int length, int[] duplication) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : numbers) {
            if (set.contains(num)) {
                duplication[0] = num;
                return true;
            }
            set.add(num);
        }
        return false;
    }

    /**
     * 思路2:根据输入数组的特点，每个数字不超过数组长度，所以当遇到某个数时，把它换到与index与之相等的位置，
     * 这样在下一次遇到这个数时，判断一下对应index上的数是不是他自己，即可得到他是否是重复的数字。
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate_2(int[] numbers, int length, int[] duplication) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[numbers[i]] != numbers[i]) {
                int temp = numbers[numbers[i]];
                numbers[numbers[i]] = numbers[i];
                numbers[i] = temp;
            } else {
                if (numbers[i] != i) {      // 防止上面的numbers[numbers[i]] != numbers[i]发生混淆
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }
}
