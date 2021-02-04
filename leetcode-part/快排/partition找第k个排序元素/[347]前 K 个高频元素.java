//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 627 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目要求时间复杂度小于 O(nlogn)，则使用partition，复杂度O(n)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap(); // num -> count
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int len = map.size();
        int[] arr = new int[len];
        int index = 0;
        for (int count : map.values()) {
            arr[index++] = count;
        }
        int left = 0, right = len - 1;
        while (left < right) {
            index = partition(arr, left, right);
            if (index == len - k) {
                left = index;
                break;
            } else if (index < len - k) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
        int[] result = new int[k];
        index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= arr[left]) {
                result[index++] = entry.getKey();
            }
        }

        return result;
    }

    private int partition(int[] arr, int left, int right) {
        int target = arr[right];
        while (left < right) {
            while (left < right && arr[left] < target) {
                left++;
            }
            arr[right] = arr[left];
            while (left < right && arr[right] >= target) {
                right--;
            }
            arr[left] = arr[right];
        }
        arr[left] = target;

        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
