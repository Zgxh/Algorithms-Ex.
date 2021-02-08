//集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有
//一个数字重复 。 
//
// 给定一个数组 nums 代表了集合 S 发生错误后的结果。 
//
// 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2,4]
//输出：[2,3]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 104 
// 1 <= nums[i] <= 104 
// 
// Related Topics 哈希表 数学 
// 👍 150 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 异或运算法：假设重复的数与缺失的数为x和y
    public int[] findErrorNums(int[] nums) {
        // 1. nums中的数与1~n之间的所有数进行异或，得到 xor = x^y
        int xor = 0;
        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        for (int num : nums) {
            xor ^= num;
        }
        // 2. 找到 x^y 的最右方第一个不为0的bit
        int rightestOneBit = 1;
        while ((rightestOneBit & xor) == 0) {
            rightestOneBit <<= 1;
        }
        // 3. 利用 rightestOneBit 把nums和1~n之间的数分成两组，组内分别异或，得到x与y
        int group1Xor = 0, group2Xor = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & rightestOneBit) != 0) {
                group1Xor ^= i;
            } else {
                group2Xor ^= i;
            }
        }
        for (int num : nums) {
            if ((num & rightestOneBit) != 0) {
                group1Xor ^= num;
            } else {
                group2Xor ^= num;
            }
        }
        // 4. 遍历一遍，确定哪个是x，哪个是y
        for (int num : nums) {
            if (num == group1Xor) {
                return new int[]{group1Xor, group2Xor};
            }
        }

        return new int[]{group2Xor, group1Xor};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
