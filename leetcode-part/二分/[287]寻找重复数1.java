//给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出
//这个重复的数。 
//
// 示例 1: 
//
// 输入: [1,3,4,2,2]
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [3,1,3,4,2]
//输出: 3
// 
//
// 说明： 
//
// 
// 不能更改原数组（假设数组是只读的）。 
// 只能使用额外的 O(1) 的空间。 
// 时间复杂度小于 O(n2) 。 
// 数组中只有一个重复的数字，但它可能不止重复出现一次。 
// 
// Related Topics 数组 双指针 二分查找 
// 👍 785 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路2：二分查找
     * 假如数组长度为len,则所有元素都在1~len-1之间,其中target不止出现一次。容易知道：
     * 若对一个在1~len-1之间的数 mid，遍历整个数组，对所有小于等于它的数字进行计数，则有：
     * 若count <= mid，则一定有mid < target;
     * 若 count > mid，则一定有 mid >= target。
     * 因此可确定二分规则。
     */
    public int findDuplicate1(int[] nums) {
        int len = nums.length;
        int left = 1, right = len - 1; // 由题目所知，所有数字都在 1~len-1之间
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
