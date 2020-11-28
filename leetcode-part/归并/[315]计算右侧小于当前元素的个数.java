//给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 num
//s[i] 的元素的数量。 
//
// 
//
// 示例： 
//
// 输入：nums = [5,2,6,1]
//输出：[2,1,1,0] 
//解释：
//5 的右侧有 2 个更小的元素 (2 和 1)
//2 的右侧仅有 1 个更小的元素 (1)
//6 的右侧有 1 个更小的元素 (1)
//1 的右侧有 0 个更小的元素
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 471 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 归并排序的思路：求逆序对。
    // 最终结果应该对应到数组元素的初始 index，所以：
    // 在排序的过程中，同时记录对应元素在原nums数组中的index，
    private int[] nums;
    private int[] index; // 存放排序后的元素的初始index
    private int[] temp; // 辅助排序
    private int[] tempIndex; // index 的辅助
    private int[] result;

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList();
        }
        this.nums = nums;
        this.temp = new int[len];
        this.index = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = i;
        }
        this.tempIndex = new int[len];
        this.result = new int[len];
        // 归并排序来计算逆序对个数
        mergeSort(0, len - 1);
        List<Integer> list = new ArrayList();
        for (int num : result) {
            list.add(num);
        }

        return list;
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
            // 每当加入左半区间的i元素时，会发现逆序对，因为右半区间中j往前的都小于左第i元素
            if (nums[i] <= nums[j]) { // 这里必须取等，当遇到左右相等的元素时，优先加入左半区间的元素，这样result中就不会算上相等的元素
                temp[k] = nums[i];
                tempIndex[k] = index[i];
                result[index[i]] += j - (mid + 1); // 更新逆序对个数
                i++;
            } else {
                temp[k] = nums[j];
                tempIndex[k] = index[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = nums[i];
            tempIndex[k] = index[i];
            result[index[i]] += j - (mid + 1); // 更新逆序对个数
            i++;
            k++;
        }
        while (j <= right) {
            temp[k] = nums[j];
            tempIndex[k] = index[j];
            j++;
            k++;
        }
        for (i = left; i <= right; i++) {
            nums[i] = temp[i];
            index[i] = tempIndex[i];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
