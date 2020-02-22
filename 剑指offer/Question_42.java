import java.util.ArrayList;

/**
 * 剑指offer第42题：和为S的2个数字
 *
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 * （对应每个测试案例，输出两个数，小的先输出。）
 *
 * @author Yu Yang
 * @create 2020-02-22 12:57
 */
public class Question_42 {

    /**
     * 思路：双指针前后往中间夹，若当前和小于sum，left++；若当前和大于sum，right--。
     * 终止条件是left和right相遇，在这个过程中找到所有的数字对，取最小乘积的作为结果。
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        ArrayList<Integer> result = new ArrayList<>();
        int left = 0, right = array.length - 1;
        int lastProduct = 0;        // 记录上一次的乘积
        while (left < right) {
            int curSum = array[left] + array[right];
            int curProduct = array[left] * array[right];
            if (curSum == sum) {
                if (result.isEmpty() || lastProduct > curProduct) {
                    result.clear();
                    result.add(array[left]);
                    result.add(array[right]);
                    lastProduct = curProduct;
                }
                left++;
            } else if (curSum < sum) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}
