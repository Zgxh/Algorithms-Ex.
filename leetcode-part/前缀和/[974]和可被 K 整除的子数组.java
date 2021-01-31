//给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。 
//
// 
//
// 示例： 
//
// 输入：A = [4,5,0,-2,-3,1], K = 5
//输出：7
//解释：
//有 7 个子数组满足其元素之和可被 K = 5 整除：
//[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 30000 
// -10000 <= A[i] <= 10000 
// 2 <= K <= 10000 
// 
// Related Topics 数组 哈希表 
// 👍 238 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 前缀和 + map : 技巧是把余数当做键
    public int subarraysDivByK(int[] A, int K) {
        int len = A.length;
        int prefixSum = 0;
        // 把前缀和对k取余，如果余数相同，则两个index之间的和肯定能被k整除
        Map<Integer, Integer> map = new HashMap(); // sum -> count
        map.put(0, 1);
        int result = 0;
        for (int i = 0; i < len; i++) {
            prefixSum += A[i];
            int modulus = (prefixSum % K + K) % K; // 为了避免被除数是负数的情况
            int count = map.getOrDefault(modulus, 0);
            result += count;
            map.put(modulus, count + 1);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
