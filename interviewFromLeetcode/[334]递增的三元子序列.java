//给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。 
//
// 数学表达式如下: 
//
// 如果存在这样的 i, j, k, 且满足 0 ≤ i < j < k ≤ n-1， 
//使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。 
//
// 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5]
//输出: true
// 
//
// 示例 2: 
//
// 输入: [5,4,3,2,1]
//输出: false 
//


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：双指针，利用了if-else if-else的条件判断。设计比较巧妙。
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {

        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int num : nums) {
            if (min >= num) { // if和else if只有遇到递减才会更新
                min = num;
            } else if (secondMin >= num) { // 假如进了这一层，说明当前secondMin肯定是当前第二小的
                secondMin = num;
            } else { // 存在大于当前第二小的，说明三个递增。
                return true;
            }
        }

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
