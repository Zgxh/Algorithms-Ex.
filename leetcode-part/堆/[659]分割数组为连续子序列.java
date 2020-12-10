//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。 
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3
//3, 4, 5
// 
//
// 示例 2： 
//
// 
//输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3, 4, 5
//3, 4, 5
// 
//
// 示例 3： 
//
// 
//输入: [1,2,3,4,4,5]
//输出: False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10000 
// 
// Related Topics 堆 贪心算法 
// 👍 253 👎 0


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // // 贪心法：对于一个元素，总是先加入到已有的序列中，因为这样可以使原有序列变得更长
    // public boolean isPossible(int[] nums) {
    //     // 统计每个数字的出现次数
    //     Map<Integer, Integer> count = new HashMap();
    //     for (int num : nums) {
    //         count.put(num, count.getOrDefault(num, 0) + 1);
    //     }
    //     int len = nums.length;
    //     for (int times : count.values()) {
    //         if (times > len / 3) { // 如果某一个字符出现的次数太多，则直接 false
    //             return false;
    //         }
    //     }
    //     // tails 存放长度大于等于3的已分割出来的子序列，其序列即将放置的下一个元素
    //     // 比如：序列[1,2,3]，则key存为 4. value对应为下一个元素为4的子序列的个数
    //     Map<Integer, Integer> tails = new HashMap();
    //     // 对于每一个元素 num，如果它还没被分配，则优先连接到已有子序列的尾部；
    //     // 如果不存在符合条件的序列，则与 num+1, num+2 组合为一个新的子序列；
    //     // 如果都不满足，则不能放入
    //     for (int num : nums) {
    //         int c0, c1, c2, c3;
    //         if ((c0 = count.get(num)) == 0) { // 该数字已经成功分配到满足条件的序列上
    //             continue;
    //         } else if ((c1 = tails.getOrDefault(num, 0)) > 0) { // 此时连接到已有序列上
    //             tails.put(num, c1 - 1);
    //             tails.put(num + 1, tails.getOrDefault(num + 1, 0) + 1);
    //         } else if ((c2 = count.getOrDefault(num + 1, 0)) > 0 && (c3 = count.getOrDefault(num + 2, 0)) > 0) {
    //             // 此时新建一个序列
    //             count.put(num + 1, c2 - 1);
    //             count.put(num + 2, c3 - 1);
    //             tails.put(num + 3, tails.getOrDefault(num + 3, 0) + 1);
    //         } else {
    //             return false;
    //         }
    //         // 该数字成功分配，出现次数 -1
    //         count.put(num, c0 - 1);
    //     }

    //     return true;
    // }


    // 堆的解法
    public boolean isPossible(int[] nums) {
        int len = nums.length;
        // 记录一个数字作为序列的末尾数字时，其对应的所有序列的长度
        // 序列长度用小顶堆存放，每次在序列后面添加元素时，总是先添加到长度小的序列上
        Map<Integer, PriorityQueue<Integer>> map = new HashMap(); // value 为小顶堆
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new PriorityQueue());
            }
            // 在末尾为 num-1 的所有序列中拿到长度最小的，删除掉
            // 然后把弹出的序列加入到末尾为 num 的集合中，长度加一
            if (map.containsKey(num - 1)) {
                PriorityQueue<Integer> valueList = map.get(num - 1);
                int preSeqLen = valueList.poll();
                if (valueList.isEmpty()) {
                    map.remove(num - 1);
                }
                map.get(num).offer(preSeqLen + 1);
            } else {
                map.get(num).offer(1);
            }
        }
        // 如果最后存在序列长度小于3的情况，则不满足条件
        for (PriorityQueue<Integer> seqLen : map.values()) {
            if (seqLen.peek() < 3) {
                return false;
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
