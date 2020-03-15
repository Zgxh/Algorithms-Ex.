/*
 * @lc app=leetcode.cn id=263 lang=java
 *
 * [263] 丑数
 */
class Solution {
    public boolean isUgly(int num) {
        if (num == 0) return false;
        while (num != 1) {
            if (num % 5 != 0) {
                break;
            }
            else {
                num /= 5;
            }
        }
        while (num != 1) {
            if (num % 3 != 0) {
                break;
            }
            else {
                num /= 3;
            }
        }
        while (num != 1) {
            if (num % 2 != 0) {
                return false;
            }
            else {
                num /= 2;
            }
        }
        return true;
    }
}

