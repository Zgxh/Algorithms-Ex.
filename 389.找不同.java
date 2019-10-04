import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=389 lang=java
 *
 * [389] 找不同
 */
class Solution {
    public char findTheDifference(String s, String t) {
        /**
         * 先把t中的每个char放入map中，如果已有这个char，对应的value就加一
         * 对于s，对每个char从map中减一，直到remove
         */
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            }
            else {
                map.put(t.charAt(i), 1);
            }
        }
        for (char c : s.toCharArray()) {
            if (map.get(c) > 1) {
                map.put(c, map.get(c) - 1);
            }
            else {
                map.remove(c);
            }
        }
        // 用map的keySet()获取剩余key的Set，转化成iterator，首次调用next()方法返回第一个元素
        return map.keySet().iterator().next();
    }
}

