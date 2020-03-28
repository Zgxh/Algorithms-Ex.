//给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。 
//
// 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0,
// 2, 5]。 
//
// 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。 
//
// 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？ 
//
// 
//
// 示例： 
//
// 输入: words = ["time", "me", "bell"]
//输出: 10
//说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 2000 
// 1 <= words[i].length <= 7 
// 每个单词都是小写字母 。 
// 
//


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 两个基本思路：
     * 思路1：利用前缀树，将每个string倒序则把后缀变为前缀，统计前缀树所有的叶子深度和即可。
     * 思路2：利用String的API，通过排序倒序后的字符串，判断是否为前缀，统计所有非前缀最大长度string的总长度即可。
     *
     */

    /**
     * 思路2
     * @param words
     * @return
     */
    public int minimumLengthEncoding1(String[] words) {

        for (int i = 0; i < words.length; i++) {
            words[i] = (new StringBuilder(words[i])).reverse().toString();
        }
        Arrays.sort(words);

        int result = 0;

        for (int i = 0; i < words.length; i++) {
            if (i + 1 >= words.length || !words[i + 1].startsWith(words[i])) {
                result += words[i].length() + 1;
            }
        }

        return result;
    }

    /**
     * 思路1
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {

        TrieNode root = new TrieNode();
        Map<TrieNode, Integer> map = new HashMap<>();
        int result = 0;

        for (int i = 0; i < words.length; i++) {
            TrieNode curNode = root;
            for (int j = words[i].length() - 1; j >= 0 ; j--) {
                curNode = curNode.getOrDefault(words[i].charAt(j));
            }
            map.put(curNode, i);
        }
        for (Map.Entry<TrieNode, Integer> entry : map.entrySet()) {
            if (entry.getKey().isLeaf == true) {
                result += words[entry.getValue()].length() + 1;
            }
        }

        return result;
    }
}

/**
 * 前缀树的结点
 */
class TrieNode {

    TrieNode[] links;
    boolean isLeaf;

    TrieNode() {
        links = new TrieNode[26];
        isLeaf = true;
    }

    public TrieNode getOrDefault(char ch) {
        if (links[ch - 'a'] == null) {
            links[ch - 'a'] = new TrieNode();
            isLeaf = false;
        }
        return links[ch - 'a'];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
