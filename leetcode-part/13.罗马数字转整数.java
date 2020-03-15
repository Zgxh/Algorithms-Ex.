/*
 * @lc app=leetcode.cn id=13 lang=java
 *
 * [13] 罗马数字转整数
 */
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] strC = s.toCharArray();
        int len = s.length();
        int result = 0;
        for (int i=len-1; i>=0; i--) {
            int now = map.get(strC[i]);
            if (i == len - 1) {
                result += now;
            }
            else {
                int last = map.get(strC[i+1]);
                if (now < last) {
                    result -= now;
                }
                else {
                    result += now;
                }
            }
        }
        return result;
    }
}

