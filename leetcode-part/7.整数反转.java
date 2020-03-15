
/*
 * @lc app=leetcode.cn id=7 lang=java
 *
 * [7] 整数反转
 */
class Solution {
    public int reverse(int x) {
        int result = 0;
        StringBuilder strb = new StringBuilder();
        boolean isNagetive = false;
        if (x < 0) {
            isNagetive = true;
            x = -x;
        }
        while (x != 0) {
            strb.append((char)(x%10));
            x = x / 10;
        }
        String str1 = strb.toString();
        char[] strChar = str1.toCharArray();
        int len = strChar.length;
        long temp = 0;
        for (int i=len-1; i>=0; i--) {
            temp += (int)(strChar[i]) * Math.pow(10, len-1-i);
        }
        result = temp > Integer.MAX_VALUE? 0 : (int) temp;
        return isNagetive? -result : result;
    }
}

