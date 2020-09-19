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
// 👍 134 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // // 方法1:利用大顶堆，堆内始终维护k个值
    // public int[] getLeastNumbers(int[] arr, int k) {
    //     PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
    //     for (int i = 0; i < k; i++) {
    //         maxHeap.offer(arr[i]);
    //     }
    //     int len = arr.length;
    //     for (int i = k; i < len; i++) {
    //         maxHeap.offer(arr[i]);
    //         maxHeap.poll();
    //     }
    //     int[] result = new int[k];
    //     for (int i = 0; i < k; i++) {
    //         result[i] = maxHeap.poll();
    //     }

    //     return result;
    // }

    // 方法2：利用快排的partition操作
    private int[] arr;

    public int[] getLeastNumbers(int[] arr, int k) {
        this.arr = arr;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int index = partition(left, right);
            if (index == k) {
                break;
            } else if (index < k) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }

        return Arrays.copyOfRange(arr, 0, k);
    }

    private int partition(int left, int right) { // 返回partition后的index
        int target = arr[right];
        while (left < right) {
            while (left < right && arr[left] < target) {
                left++;
            }
            arr[right] = arr[left];
            while (left < right && arr[right] >= target) {
                right--;
            }
            arr[left] = arr[right];
        }
        arr[left] = target;

        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
