//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// 👍 265 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 归并排序解决逆序对问题
    private int result = 0;
    private int[] nums;
    private int[] temp;

    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        this.nums = nums;
        this.temp = new int[len];
        mergeSort(0, len - 1);

        return result;
    }

    private void mergeSort(int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, right, mid);
    }

    private void merge(int left, int right, int mid) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            // 当左半区间第i个数小于右半区间的第j数时，产生逆序对，个数为右半区间j前面的所有数字的个数
            if (nums[i] <= nums[j]) { // 这里必须取等
                temp[k++] = nums[i++];
                result += j - (mid + 1);
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
            result += j - (mid + 1);
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        for (k = left; k <= right; k++) {
            nums[k] = temp[k];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
