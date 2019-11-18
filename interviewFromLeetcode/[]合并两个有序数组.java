//给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。 
//
// 说明: 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 示例: 
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6] 
// Related Topics 数组 双指针



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 思路：先把nums1中的所有元素后移n位，然后利用双指针，依次加入nums1和nums2中最小的元素，
     * 直到有一个遍历完。然后把nums2中剩余的添加到后面，nums1因为之前已经后移过，所以已经满足要求。
     * 这就是归并排序中的归并操作！！！
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 把nums1元素后移n位
        for (int i = m - 1; i >= 0; i--) {
            nums1[n + i] = nums1[i];
        }
        int i = n;
        int j = 0;
        int index = 0;
        // 比较nums1和nums2，依次加入最小值
        while (i <= n + m - 1 && j < n) {
            if (nums1[i] < nums2[j]) {
                nums1[index++] = nums1[i++];
            } else {
                nums1[index++] = nums2[j++];
            }
        }
        // 加入nums2中剩余的元素
        while (j < n) {
            nums1[index++] = nums2[j++];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
