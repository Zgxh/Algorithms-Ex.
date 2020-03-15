/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个有序数组的中位数
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        // 保证A的长度小于等于B的长度
        if (m > n) {
            int[] temp = A; 
            A = B; 
            B = temp;
            int tmp = m; 
            m = n; 
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            // <i和<j的部分为左半部分，>=i和>=j的部分为右半部分
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            //此时i太小了
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1;
            }
            //此时i太大了
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1;
            }
            //i正好是我们想要的
            else {
                int maxLeft = 0;
                //确定左半部分的最大值
                if (i == 0) {
                    maxLeft = B[j - 1];
                }
                else if (j == 0) {
                    maxLeft = A[i - 1];
                }
                else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                //奇数情况，左半部分比右半部分多一个元素，左半部分的最大值即为中位数
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                int minRight = 0;
                //确定右半部分的最小值
                if (i == m) {
                    minRight = B[j];
                }
                else if (j == n) {
                    minRight = A[i];
                }
                else {
                    minRight = Math.min(B[j], A[i]);
                }
                //偶数情况返回两个值的平均值
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
// @lc code=end

