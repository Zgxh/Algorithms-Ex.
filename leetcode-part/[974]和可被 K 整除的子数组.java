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


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：利用前缀和。sum[0~i]-sum[0~j]即为 sum[j~i]，
     * 若sum[0~i] 与 sum[0~j]对 K 的取余相同，则 sum[j~i] 对 K 取余即为0.
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, result = 0;

        for (int num : A) {
            sum += num;
            int remainder = (sum % K + K) % K;
            int numOfSameRemainder = map.getOrDefault(remainder, 0);
            result += numOfSameRemainder;
            map.put(remainder, numOfSameRemainder + 1);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
