/*
 * @lc app=leetcode.cn id=31 lang=java
 *
 * [31] 下一个排列
 */
class Solution {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len == 0 || len == 1) 
            return;
        for (int i=len-2; i>=0; i--) {
            if (nums[i+1] > nums[i]) {
                for (int j=i+1; j<=i+(len-i)/2; j++) {
                    swap(nums, j, len+i-j);
                }
                for (int k=i+1; k<len; k++) {
                    if (nums[k] > nums[i]) {
                        swap(nums, k, i);
                        for (int m=k+1; m<len; m++) {
                            if (nums[k] < nums[m])
                                return;
                            else
                                swap(nums, k, m);
                        }
                        return;
                    }
                }
            }
        }
        for (int i=0; i<len/2; i++) {
            swap(nums, i, len-1-i);
        }
    }
    public void swap(int[] arr, int one, int another) {
        int temp = arr[one];
        arr[one] = arr[another];
        arr[another] = temp;
    }
}

