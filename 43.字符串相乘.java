/*
 * @lc app=leetcode.cn id=43 lang=java
 *
 * [43] 字符串相乘
 */
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        int length1 = num1.length();
        int length2 = num2.length();
        int[] product = new int[length1 + length2];
        for (int i = length1 - 1; i >= 0; i--) {
            for (int j = length2 - 1; j >= 0; j--) {
                int bit1 = num1.charAt(i) - 48;
                int bit2 = num2.charAt(j) - 48;
                product[i + j] += bit1 * bit2;
                if (i + j > 0 && product[i + j] >= 10) {
                    product[i + j - 1] += product[i + j] / 10;
                    product[i + j] = product[i + j] % 10;
                }
            }
        }
        for (int i = 0; i < length1 + length2 - 1; i++) {
            res.append(product[i]);
        }
        return res.toString();
    }
}

