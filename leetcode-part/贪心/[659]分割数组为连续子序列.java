//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。 
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3
//3, 4, 5
// 
//
// 
//
// 示例 2： 
//
// 输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3, 4, 5
//3, 4, 5
// 
//
// 
//
// 示例 3： 
//
// 输入: [1,2,3,4,4,5]
//输出: False
// 
//
// 
//
// 提示： 
//
// 
// 输入的数组长度范围为 [1, 10000] 
// 
//
// 
// Related Topics 堆 贪心算法 
// 👍 134 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 贪心法：对于一个元素，总是先加入到已有的序列中，因为这样可以使原有序列变得更长
    public boolean isPossible(int[] nums) {
        // 统计每个数字的出现次数
        Map<Integer, Integer> count = new HashMap();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        int len = nums.length;
        for (int times : count.values()) {
            if (times > len / 3) { // 如果某一个字符出现的次数太多，则直接 false
                return false;
            }
        }
        // tails 存放长度大于等于3的已分割出来的子序列，其序列即将放置的下一个元素
        // 比如：序列[1,2,3]，则key存为 4. value对应为下一个元素为4的子序列的个数
        Map<Integer, Integer> tails = new HashMap();
        // 对于每一个元素 num，如果它还没被分配，则优先连接到已有子序列的尾部；
        // 如果不存在符合条件的序列，则与 num+1, num+2 组合为一个新的子序列；
        // 如果都不满足，则不能放入
        for (int num : nums) {
            int c0, c1, c2, c3;
            if ((c0 = count.get(num)) == 0) { // 该数字已经成功分配到满足条件的序列上
                continue;
            } else if ((c1 = tails.getOrDefault(num, 0)) > 0) { // 此时连接到已有序列上
                tails.put(num, c1 - 1);
                tails.put(num + 1, tails.getOrDefault(num + 1, 0) + 1);
            } else if ((c2 = count.getOrDefault(num + 1, 0)) > 0 && (c3 = count.getOrDefault(num + 2, 0)) > 0) {
                // 此时新建一个序列
                count.put(num + 1, c2 - 1);
                count.put(num + 2, c3 - 1);
                tails.put(num + 3, tails.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
            // 该数字成功分配，出现次数 -1
            count.put(num, c0 - 1);
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
