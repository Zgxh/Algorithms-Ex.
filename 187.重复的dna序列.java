import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=187 lang=java
 *
 * [187] 重复的DNA序列
 */
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        Set<String> set = new HashSet<>();
        Set<String> tmp = new HashSet<>();
        if (len >= 11) {
            for (int i = 0; i <= len - 10; i++) {
                String sub = s.substring(i, i+10);
                if (!set.add(sub)) {
                    tmp.add(sub);
                }
            }
        }
        return new LinkedList<>(tmp);
    }
}

