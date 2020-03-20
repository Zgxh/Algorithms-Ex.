//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路1：使用大顶堆，留下最小的k个。
     * 思路2：即下面的代码。借助快排partition的思想，找到index==k的分界点。前面的k个数就是最小的。
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {

        if (arr == null || k <= 0) {
            return new int[0];
        }
        if (arr.length <= k) {
            return arr;
        }

        partitionArray(arr, 0, arr.length - 1, k);

        return Arrays.copyOfRange(arr, 0, k);
    }

    public void partitionArray(int[] arr, int low, int high, int k) {

        int index = partition(arr, low, high);
        if (index == k) {
            return;
        } else if (index < k) {
            partitionArray(arr, index + 1, high, k);
        } else {
            partitionArray(arr, low, index - 1, k);
        }
    }

    /**
     * 快排的partition
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public int partition(int[] arr, int low, int high) {

        int temp = arr[low];
        while (low < high) {
            while (low < high && arr[high] > temp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= temp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
