/**
 * 剑指offer第35题：数组中的逆序对
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。
 * 即输出P%1000000007
 *
 * @author Yu Yang
 * @create 2020-02-12 21:56
 */
public class Question_35 {

    /**
     * 思路：利用归并排序的思想。
     */
    public int pairCount = 0;
    public int InversePairs(int[] array) {
        mergeSort(array, 0, array.length - 1);
        return pairCount;
    }

    public void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    public void merge(int[] array, int left, int mid, int right) {
        int i = left, j = mid + 1, k = 0;
        int[] temp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
                pairCount = (pairCount + (mid - i + 1)) % 1000000007; // 这里要这么写
            }
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        for (k = 0; k < temp.length; k++) {
            array[left + k] = temp[k];
        }
    }
}
