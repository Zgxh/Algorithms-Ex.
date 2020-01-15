//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 注意: 
//不能使用代码库中的排序函数来解决这道题。 
//
// 示例: 
//
// 输入: [2,0,2,1,1,0]
//输出: [0,0,1,1,2,2] 
//
// 进阶： 
//
// 
// 一个直观的解决方案是使用计数排序的两趟扫描算法。 
// 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 排序 数组 双指针




//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：运用快排partition改进的算法：把数据划分为三个区域，界限为zero和two两个指针。
     * <=zero部分存放0，>=two的位置存放2，中间部分存放1。
     * 遇到1不管，i自增；遇到0，0的空间变大一格，交换i和zero上的数据；
     * 遇到2,2的空间增大一格，交换i和two上的数据。
     * 注意每次操作完后，i应不应该自增。
     * 只对数据遍历了一次，时间复杂度：O(n)
     * @param nums
     */
    public void sortColors(int[] nums) {
        int zero = -1;
        int two = nums.length;
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                two--;
                swap(nums, i, two); // 此时two位置上的数被交换到i位置，该数还没被判断过，不进行i自增
            } else {
                zero++;
                swap(nums, i, zero);
                i++;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
