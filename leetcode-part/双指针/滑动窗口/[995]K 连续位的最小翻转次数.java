//在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0
//。 
//
// 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//输入：A = [0,1,0], K = 1
//输出：2
//解释：先翻转 A[0]，然后翻转 A[2]。
// 
//
// 示例 2： 
//
// 
//输入：A = [1,1,0], K = 2
//输出：-1
//解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
// 
//
// 示例 3： 
//
// 
//输入：A = [0,0,0,1,0,1,1,0], K = 3
//输出：3
//解释：
//翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
//翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
//翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 30000 
// 1 <= K <= A.length 
// 
// Related Topics 贪心算法 Sliding Window 
// 👍 181 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // // 方法1：差分数组。 时间 O(n),空间 O(n)
    // // 当某个位置i是0时，从i开始的长度为K的段都要变成1，因此i+k位置比i位置就少反转一次
    // public int minKBitFlips(int[] A, int K) {
    //     int n = A.length;
    //     int[] diff = new int[n + 1]; // diff[i] = A[i]的反转次数 - A[i-1] 的反转次数
    //     int ans = 0, revCnt = 0; // revCnt 代表当前位置的真实反转次数
    //     for (int i = 0; i < n; ++i) {
    //         revCnt += diff[i];
    //         // 如果当前位置反转了revCnt后还是0，则需要再反转一次
    //         if ((A[i] + revCnt) % 2 == 0) {
    //             // 如果已经是最后一段k长度段了，则无法完成
    //             if (i + K > n) {
    //                 return -1;
    //             }
    //             ++ans;
    //             ++revCnt;
    //             --diff[i + K];
    //         }
    //     }

    //     return ans;
    // }

    // 滑动窗口法：时间O(n)，空间O(1)
    // 当访问完i位置后，如果该位置反转了，则A[i] +=2，当访问到i+K位置时，就可以知道i位置是否反转过
    public int minKBitFlips(int[] A, int K) {
        int len = A.length;
        int result = 0;
        int reverseCount = 0; // 代表当前反转次数，模2
        for (int i = 0; i < len; i++) {
            // 如果i-K位置反转过，则该位置反转次数-1
            if (i >= K && A[i - K] > 1) {
                reverseCount ^= 1;
            }
            // 如果经过之前的反转后，值还是0
            if (A[i] == reverseCount) {
                if (i + K > len) {
                    return -1;
                }
                result++;
                // 反转次数+1
                reverseCount ^= 1;
                // 标记该位置为反转过
                A[i] += 2;
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
