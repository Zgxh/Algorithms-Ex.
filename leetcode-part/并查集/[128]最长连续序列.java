//给定一个未排序的整数数组，找出最长连续序列的长度。 
//
// 要求算法的时间复杂度为 O(n)。 
//
// 示例: 
//
// 输入: [100, 4, 200, 1, 3, 2]
//输出: 4
//解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。 
// Related Topics 并查集 数组 
// 👍 524 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // // 哈希表写法
    // public int longestConsecutive(int[] nums) {
    //     if (nums.length == 0) {
    //         return 0;
    //     }
    //     Set<Integer> set = new HashSet();
    //     for (int num : nums) {
    //         set.add(num);
    //     }
    //     int maxLen = 1;
    //     for (int begin : set) {
    //         if (!set.contains(begin - 1)) { // 不遍历重复的子序列
    //             int curLen = 1;
    //             int shift = 1;
    //             while (set.contains(begin + shift)) {
    //                 shift++;
    //                 curLen++;
    //             }
    //             maxLen = Math.max(maxLen, curLen);
    //         }
    //     }

    //     return maxLen;
    // }


    /**
     * 并查集
     *
     * 对相邻的数放在同一个连通分量中，其中连通分量的根是该连通子图中最大的值。
     * 这样对所有的数寻找连通性，最后通过比较自身与连通分量的根来寻找最大长度。
     */

    private Map<Integer, Integer> root;

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        root = new HashMap();
        // 初始化并查集，每个元素的根结点是自身
        for (int num : nums) {
            root.put(num, num);
        }
        for (int num : nums) {
            union(num, num + 1);
        }
        int maxLen = 1;
        for (int num : nums) {
            maxLen = Math.max(maxLen, findTotalRoot(num) - num + 1);
        }

        return maxLen;
    }

    // 合并两个组，输入必须使 num1 < num2
    private void union(int num1, int num2) {
        Integer boss1 = findTotalRoot(num1), boss2 = findTotalRoot(num2);
        if (boss1 == null || boss2 == null || boss1 == boss2) { // 此时不需要合并
            return;
        }

        root.put(boss1, boss2); // 合并并查集，让2变成1的爹，这样最终一个集里的大boss就是连续序列的最大值
    }

    // 找一个数的大Boss
    private Integer findTotalRoot(int num) {
        if (!root.containsKey(num)) {
            return null;
        }
        int totalRoot = num;
        while (root.get(totalRoot) != totalRoot) {
            num = totalRoot;
            totalRoot = root.get(totalRoot);
            root.put(num, totalRoot); // 压缩路径
        }

        return totalRoot;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
