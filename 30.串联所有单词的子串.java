import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=30 lang=java
 *
 * [30] 串联所有单词的子串
 */
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }
        int lengthOfEachWord = words[0].length();
        int lengthOfWords = words.length * words[0].length();
        int lengthOfString = s.length();
        HashMap<String, Integer> hashMapOfWords = new HashMap<String, Integer>();
        for (String word : words) {
            int value = hashMapOfWords.getOrDefault(word, 0);
            hashMapOfWords.put(word, value + 1);
        }
        for (int i = 0; i < lengthOfString - lengthOfWords + 1; i++) {
            HashMap<String, Integer> hashMapOfSubstring = new HashMap<String, Integer>();
            String subString = new String(s.substring(i, i+lengthOfWords));
            for (int j = 0; j < lengthOfWords; j += lengthOfEachWord) {
                String subSubString = new String(subString.substring(j, j + lengthOfEachWord));
                if (hashMapOfWords.containsKey(subSubString)) {
                    int valueOfSubString = hashMapOfSubstring.getOrDefault(subSubString, 0);
                    if (valueOfSubString < hashMapOfWords.get(subSubString)) {
                        hashMapOfSubstring.put(subSubString, valueOfSubString + 1);
                    }
                    else {
                        break;
                    }
                }
                else {
                    break;
                }
            }
            if (hashMapOfWords.equals(hashMapOfSubstring)) {
                res.add(i);
            }
        }
        return res;
    }
}


