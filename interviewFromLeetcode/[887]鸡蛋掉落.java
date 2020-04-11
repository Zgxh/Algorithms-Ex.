//你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N 共有 N 层楼的建筑。 
//
// 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。 
//
// 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。 
//
// 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。 
//
// 你的目标是确切地知道 F 的值是多少。 
//
// 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？ 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：K = 1, N = 2
//输出：2
//解释：
//鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
//否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
//如果它没碎，那么我们肯定知道 F = 2 。
//因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
// 
//
// 示例 2： 
//
// 输入：K = 2, N = 6
//输出：3
// 
//
// 示例 3： 
//
// 输入：K = 3, N = 14
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= K <= 100 
// 1 <= N <= 10000 
// 
// Related Topics 数学 二分查找 动态规划



//leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//    public int superEggDrop(int K, int N) {
//
//        int[][] dp = new int[K + 1][N + 1]; // dp[i][j]代表i个鸡蛋，j层楼的结果
//
//        for (int i = 1; i <= N; i++) {
//            dp[0][i] = 0; // no egg
//            dp[1][i] = i; // one egg, need traversal: N
//        }
//        for (int i = 1; i <= K; i++) {
//            dp[i][0] = 0; // zero height
//        }
//
//        for (int k = 2; k <= K; k++) {
//            for (int n = 1; n <= N; n++) {
//                int min = n;
//                for (int i = 1; i <= n; i++) { // 碎了就少个蛋，然后在i-1层中继续摔；没碎就向上走，还是k个蛋，在上面的n-i个中试，因为在i层摔了一次，所以+1
//                    min = Math.min(min, 1 + Math.max(dp[k - 1][i - 1], dp[k][n - i]));
//                }
//                dp[k][n] = min;
//            }
//        }
//        return dp[K][N];
//    }
//}
class Solution {

    /**
     * 思想:当在某层楼x上扔鸡蛋时，两种情况：1）碎。2）不碎。若碎，变成dp(K-1, x-1);
     * 若不碎，变成dp(K, N-x)。所以最坏情况次数即为 1 + max(dp(K-1, x-1), dp(K, N-x)).
     * 遍历所有的x，要求最坏情况下最少的移动次数，即找所有最坏情况的min值。
     */

    Map<Integer, Integer> cache = new HashMap<>(); // 设置缓存，避免重复计算

    public int superEggDrop(int K, int N) {
        if (N == 0) {
            return 0;
        }
        if (K == 1) {
            return N;
        }

        Integer key = N * 1000 + K; // K <= 100， 相当于自己实现hash编码
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        // 二分，分别计算次数,找到合适的low和high
        int low = 1, high = N;
        while (low + 1 < high) { // low + 1 的原因是除法的向下取整
            int mid = (low + high) / 2;
            int lowVal = superEggDrop(K - 1, mid - 1);
            int highVal = superEggDrop(K, N - mid);

            if (lowVal < highVal) {
                low = mid;
            } else if (lowVal > highVal) {
                high = mid;
            } else {
                low = high = mid;
            }
        }
        int minimum = 1 + Math.min( // 分别求low 和 high扔鸡蛋的最坏次数，因为二分找到的low和high不一定相等
                Math.max(superEggDrop(K - 1, low - 1), superEggDrop(K, N - low)),
                Math.max(superEggDrop(K - 1, high - 1), superEggDrop(K, N - high))
        );
        cache.put(key, minimum);

        return cache.get(key);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
