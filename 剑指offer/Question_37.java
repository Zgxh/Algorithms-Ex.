/**
 * 剑指offer第37题：数字在排序数组中出现的次数
 *
 * 统计一个数字在排序数组中出现的次数。
 *
 * @author Yu Yang
 * @create 2020-02-16 16:46
 */
public class Question_37 {

    /**
     * 思路：二分法先找到目标，然后向前向后统计该数字出现的次数。
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int[] array , int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int left = 0, right = array.length;
        int mid = (left + right) / 2;
        while (left < right) { // 二分找目标
            mid = (left + right) / 2;
            if (array[mid] == k) {
                break;
            }
            if (array[mid] > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (array[mid] != k) { // 没找到
            return 0;
        }
        // 找到了，向前向后统计
        int count = 1;
        left = mid - 1;
        right = mid + 1;
        while (left >= 0 && array[left] == k) {
            count++;
            left--;
        }
        while (right < array.length && array[right] == k) {
            count++;
            right++;
        }
        return count;
    }
}
