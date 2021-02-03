//给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和
//最接近 target （最接近表示两者之差的绝对值最小）。 
//
// 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。 
//
// 请注意，答案不一定是 arr 中的数字。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [4,9,3], target = 10
//输出：3
//解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
// 
//
// 示例 2： 
//
// 输入：arr = [2,3,5], target = 10
//输出：5
// 
//
// 示例 3： 
//
// 输入：arr = [60864,25176,27249,21296,20204], target = 56803
//输出：11361
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^4 
// 1 <= arr[i], target <= 10^5 
// 
// Related Topics 数组 二分查找 
// 👍 124 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // [双]二分查找
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int len = arr.length;
        // 求前缀和
        int[] prefix = new int[len + 1];
        for (int i = 1; i <= len; ++i) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }
        // 目标数字一定在 1~arr[len-1] 之间
        int left = 1, right = arr[len - 1];
        int result = -1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 在 arr 中二分搜索 mid 的左边界
            int index = Arrays.binarySearch(arr, mid);
            if (index < 0) { // 小于0代表没找到，返回的是插入点x位置：-x-1
                index = -index - 1; // 求出插入点的位置
            }
            int cur = prefix[index] + (len - index) * mid;
            if (cur <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int chooseSmall = sum(arr, result), chooseBig = sum(arr, result + 1);

        return Math.abs(chooseSmall - target) <= Math.abs(chooseBig - target) ? result : result + 1;
    }

    public int sum(int[] arr, int x) {
        int result = 0;
        for (int num : arr) {
            result += Math.min(num, x);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
