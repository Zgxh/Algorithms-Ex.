//给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。 
//
// 注意：1 ≤ k ≤ n ≤ 109。 
//
// 示例 : 
//
// 
//输入:
//n: 13   k: 2
//
//输出:
//10
//
//解释:
//字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
// 
// 👍 174 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 十叉树法。参考：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/yi-tu-sheng-qian-yan-by-pianpianboy/
    public int findKthNumber(int n, int k) {
        int curNum = 1;
        k = k - 1; // 0 是第一个，0没有对应的子树，不需要考虑
        while (k > 0) {
            // 计算以 curNum 为根的子树上的小于 n 的所有结点的数目
            int count = getNumCount(n, curNum, curNum + 1);
            if (count <= k) { // 目标数字不在该子树上
                curNum += 1; // 移动到下一棵子树
                k -= count; // 跳过当前子树上的所有节点
            } else { // 目标数字在该子树上，需要细分该子树
                curNum *= 10; // 进入该子树的最左侧叶子结点
                k -= 1; // 跳过该子树的根
            }
        }

        return curNum;
    }

    // 统计以 first 为根的子树中，结点值小于 n 的结点的数目
    // first 代表当前层的起点，last 代表当前层的终点
    // 比如: first=10,last=20，即该轮只统计10-19
    private int getNumCount(int n, long first, long last) {
        int count = 0;
        // 按层统计，每个循环统计一层
        while (first <= n) {
            // 计算从 first 到 last 之间有多少个数字
            count += Math.min(n + 1, last) - first;
            first *= 10;
            last *= 10;
        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
