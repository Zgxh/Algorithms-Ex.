import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=313 lang=java
 *
 * [313] 超级丑数
 */
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        /**
         * 目前想到的非最优解： 动态规划 + 多指针。
         * 基本思路跟丑数2相似。设置int[k]存放每个质因数的使用次数，每次比较不用质因数
         * 与对应结果数组result[]中元素的乘积，找到最小值，然后加入结果数组中。
         * 之后重新遍历一遍，把等于这个最小值的所有质因数对应的使用次数都加一。
         */
        int k = primes.length;
        int[] counts = new int[k]; // 存放k个质因数的使用次数
        int[] result = new int[n]; // 存放结果
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            int[] products = new int[k]; // 存放当前待比较的乘积
            for (int j = 0; j < k; j++) {
                products[j] = primes[j] * result[counts[j]];
            }
            Arrays.sort(products);
            result[i] = products[0];
            for (int j = 0; j < k; j++) {
                if (primes[j] * result[counts[j]] == products[0]) {
                    counts[j]++;
                }
            }
        }
        return result[n - 1];
    }
}

