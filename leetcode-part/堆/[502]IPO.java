//假设 力扣（LeetCode）即将开始其 IPO。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限
//，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。 
//
// 给定若干个项目。对于每个项目 i，它都有一个纯利润 Pi，并且需要最小的资本 Ci 来启动相应的项目。最初，你有 W 资本。当你完成一个项目时，你将获得纯
//利润，且利润将被添加到你的总资本中。 
//
// 总而言之，从给定项目中选择最多 k 个不同项目的列表，以最大化最终资本，并输出最终可获得的最多资本。 
//
// 示例 1: 
//
// 输入: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
//
//输出: 4
//
//解释:
//由于你的初始资本为 0，你尽可以从 0 号项目开始。
//在完成后，你将获得 1 的利润，你的总资本将变为 1。
//此时你可以选择开始 1 号或 2 号项目。
//由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
//因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
// 
//
// 
//
// 注意: 
//
// 
// 假设所有输入数字都是非负整数。 
// 表示利润和资本的数组的长度不超过 50000。 
// 答案保证在 32 位有符号整数范围内。 
// 
//
// 
// Related Topics 堆 贪心算法 
// 👍 67 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 大顶堆 + 小顶堆结合的方式
    // 优化：在计算前，加一步判断，是否所有项目的启动资金都小于初始资金，这样可以等价为在所有项目中寻找最大利润的k个项目
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        // // 首先判断是否所有项目的启动资金都小于等于 W，即是否满足 speedUp 条件
        // boolean speedUp = true;
        // for (int cap : Capital) {
        //     if (cap > W) {
        //         speedUp = false;
        //         break;
        //     }
        // }
        // // 如果满足 speedUp 条件，则问题等价于寻找前 k 个最大利润的项目
        // if (speedUp) {
        //     PriorityQueue<Integer> maxHeap = new PriorityQueue();
        //     for (int profit : Profits) {
        //         maxHeap.offer(profit);
        //         if (maxHeap.size() > k) {
        //             maxHeap.poll();
        //         }
        //     }
        //     for (int profit : maxHeap) {
        //         W += profit;
        //     }

        //     return W;
        // }

        // 如果不满足 speedUp 条件
        // 把每个项目打包成数组，对应启动资金与利润，按照启动资金要求来建立小顶堆
        int len = Profits.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < len; i++) {
            minHeap.offer(new int[]{Capital[i], Profits[i]});
        }
        // 用大顶堆来寻找满足启动资金要求的最大利润项目
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        int result = 0;
        while (k > 0) {
            while (!minHeap.isEmpty() && minHeap.peek()[0] <= W) {
                maxHeap.offer(minHeap.poll()[1]);
            }
            // 如果有项目可投资，则投资最大回报的项目，然后更新当前的可用资金
            if (!maxHeap.isEmpty()) {
                W += maxHeap.poll();
            } else {
                break;
            }
            k--;
        }

        return W;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
