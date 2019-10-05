import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 求众数
 */

// @lc code=start
class Solution {

    /**
     * 遍历数组，统计每个元素出现的次数并用hashmap储存，最后遍历key值找出要求的元素。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int len = nums.length / 2;
        for (int key : map.keySet()) {
            if (map.get(key) > len) {
                return key;
            }
        }
        return -1;
    }
}
// @lc code=end

