/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 */
class Solution {
    public int maxArea(int[] height) {
        /**
         * 双指针放在数组的首尾，哪个height小就往中间移一位。
         */
        int left = 0;
        int right = height.length - 1;
        int maxCubage = (right - left) * Math.min(height[left], height[right]);
        while (left < right) {
            if (height[left] < height[right]) {
                left++;
                maxCubage = Math.max(maxCubage, (right - left) * Math.min(height[left], height[right]));
            }
            else {
                right--;
                maxCubage = Math.max(maxCubage, (right - left) * Math.min(height[left], height[right]));
            }
        }
        return maxCubage;
    }
}

