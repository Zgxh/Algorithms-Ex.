/*
 * @lc app=leetcode.cn id=58 lang=java
 *
 * [58] 最后一个单词的长度
 * 
 * 主要思想： 使用String.split()把字符串按空格分隔成子串。
 * 1. 如果数组长度大于0，则存在单词，返回数组最后一个位置的字符串长度。
 * 2. 如果数组长度为0，则原字符串中所有字符都是空格，返回长度0。
 */
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String[] splitString = s.split(" ");
        if (splitString.length > 0) {
            return splitString[splitString.length - 1].length();
        }
        return 0;
    }
}

