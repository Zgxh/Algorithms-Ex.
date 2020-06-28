/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 * 
 * 思路：滑块右移，记录最小子串长度。
 * 
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if (len == 0) 
            return 0;
        int left = 0; // 滑块左右端点
        int right = -1;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (nums[i] >= s) 
                return 1;
            else {
                sum += nums[i];
            }
            while (sum >= s) {
                right = i;
                int curLength = right - left + 1;
                if (minLength > curLength) {
                    minLength = curLength;
                }
                sum -= nums[left++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}

