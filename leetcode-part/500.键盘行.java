import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode.cn id=500 lang=java
 *
 * [500] 键盘行
 */
class Solution {
    public String[] findWords(String[] words) {
        // 把键盘字母存入hashmap中
        String[] strings = new String[] {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        List<String> result = new ArrayList<String>();
        int i = 1;
        HashMap<Character, Integer> map = new HashMap<>();
        for (String string : strings) {
            for (char c : string.toCharArray()) {
                map.put(c, i);
            }
            for (char c : string.toUpperCase().toCharArray()) {
                map.put(c, i);
            }
            i++;
        }
        // 判断key对应的value是否相同，所有都相同则可以加入list中
        for (String word : words) {
            int compare = map.get(word.charAt(0));
            boolean flag = false;
            for (int j = 0; j < word.length(); j++) {
                if (map.get(word.charAt(j)) != compare) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                result.add(word);
            }
        }
        // 采用List的API转换为字符串数组
        return result.toArray(new String[result.size()]);
    }
}

