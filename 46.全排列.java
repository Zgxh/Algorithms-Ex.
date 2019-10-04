

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 */
class Solution {
    private List<List<Integer>> list = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) 
            return list;
        recursion(nums, 0, nums.length - 1);
        return list;
    }
    private void recursion(int[] nums, int begin, int end) {
        if (begin == end) {
            List<Integer> listIn = new ArrayList<>();
            for (int elem : nums) {
                listIn.add(elem);
            }
            list.add(listIn);
            return;
        }
        for (int i=begin; i<=end; i++) {
            swap(nums, i, begin);
            recursion(nums, begin+1, end);
            swap(nums, i, begin);
        }
    } 
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}