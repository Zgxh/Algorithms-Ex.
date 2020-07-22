//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2
//] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。 
//
// 示例 1： 
//
// 输入：[3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 输入：[2,2,2,0,1]
//输出：0
// 
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sor
//ted-array-ii/ 
// Related Topics 二分查找 
// 👍 113 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 思路：有序数组，使用二分查找。
     * 时间复杂度 O(log n)
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int len = numbers.length;
        if (len == 0) {
            return -1;
        }
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1); // + 运算符 比 >> 运算符的优先级高
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right--;
            }
        }

        return numbers[left];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
