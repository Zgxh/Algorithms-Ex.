/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */
class Solution {
    public String longestPalindrome(String s) {
        /**
         * 选取中心轴，以轴为中心向两侧进行扩展，直到遇到数组边界或者不对称为止。
         * 注意轴可以为空档，也可以为数组内的元素。前者为偶数长子串情况，后者为
         * 奇数长子串情况。分别对两种情况进行处理。
         */
        if (s == null || s.length() == 0) {
            return "";
        }
        int length = s.length();
        if (length == 1) {
            return s;
        }
        int maxSubLength = 1;
        String result = s.substring(0, 1);
        for (int i = 0; i < length; i++) {
            // 偶数长子串情况
            for (int k = 0; i - k >= 0 && i + 1 + k <= length - 1; k++) {
                if (s.charAt(i - k) != s.charAt(i + 1 + k)) {
                    break;
                }
                if (maxSubLength < 2 * (k + 1)) {
                    maxSubLength = 2 * (k + 1);
                    result = s.substring(i - k, i + 2 + k);
                }
            }
            // 奇数长子串情况
            for (int k = 1; i - k >= 0 && i + k <= length - 1; k++) {
                if (s.charAt(i - k) != s.charAt(i + k)) {
                    break;
                }
                if (maxSubLength < 2 * k + 1) {
                    maxSubLength = 2 * k + 1;
                    result = s.substring(i - k, i + k + 1);
                }
            }
        }
        return result;
    }
}

