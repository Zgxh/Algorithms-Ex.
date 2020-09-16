//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 465 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 1. 按字典序对字符串进行排序
    // public List<List<String>> groupAnagrams(String[] strs) {
    //     List<List<String>> result = new ArrayList();
    //     Map<String, List<String>> map = new HashMap();
    //     for (String str : strs) {
    //         char[] charArr = str.toCharArray();
    //         Arrays.sort(charArr);
    //         List<String> temp = map.getOrDefault(new String(charArr), new ArrayList());
    //         temp.add(str);
    //         map.put(new String(charArr), temp);
    //     }
    //     for (List<String> list : map.values()) {
    //         result.add(list);
    //     }

    //     return result;
    // }

    // 2. 对字符进行计数
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList();
        Map<String, List<String>> map = new HashMap();
        for (String str : strs) {
            int[] countChar = new int[26];
            StringBuilder sb = new StringBuilder();
            for (char ch : str.toCharArray()) {
                countChar[ch - 'a'] += 1;
            }
            for (int num : countChar) {
                sb.append(num);
                sb.append("#");
            }
            List<String> temp = map.getOrDefault(sb.toString(), new ArrayList());
            temp.add(str);
            map.put(sb.toString(), temp);
        }
        for (List<String> list : map.values()) {
            result.add(list);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
