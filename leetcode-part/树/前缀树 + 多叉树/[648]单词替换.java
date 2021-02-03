//在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，
//跟随着单词 other(其他)，可以形成新的单词 another(另一个)。 
//
// 现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。 
//
// 你需要输出替换之后的句子。 
//
// 
//
// 示例： 
//
// 输入：dict(词典) = ["cat", "bat", "rat"] sentence(句子) = "the cattle was rattled by
// the battery"
//输出："the cat was rat by the bat"
// 
//
// 
//
// 提示： 
//
// 
// 输入只包含小写字母。 
// 1 <= dict.length <= 1000 
// 1 <= dict[i].length <= 100 
// 1 <= 句中词语数 <= 1000 
// 1 <= 句中词语长度 <= 1000 
// 
// Related Topics 字典树 哈希表 
// 👍 72 👎 0


import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Solution {

        /**
         * 思路：前缀树
         * @param dict
         * @param sentence
         * @return
         */
        public String replaceWords(List<String> dict, String sentence) {
            // build the prefix tree
            TrieNode root = new TrieNode();
            for (String prefix : dict) {
                TrieNode pointer = root;
                for (char ch : prefix.toCharArray()) {
                    if (pointer.next[ch - 'a'] == null) {
                        pointer.next[ch - 'a'] = new TrieNode();
                    }
                    pointer = pointer.next[ch - 'a'];
                }
                pointer.isWord = true;
            }
            // check the prefix in the dict tree for each word in the sentence
            StringBuilder result = new StringBuilder();
            for (String word : sentence.split(" ")) {
                TrieNode pointer = root;
                StringBuilder temp = new StringBuilder();
                for (char ch : word.toCharArray()) {
                    if (pointer.next[ch - 'a'] == null) {
                        break;
                    }
                    pointer = pointer.next[ch - 'a'];
                    temp.append(ch);
                    if (pointer.isWord) {
                        result.append(temp);
                        result.append(' ');
                        break;
                    }
                }
                if (!pointer.isWord) {
                    result.append(word);
                    result.append(" ");
                }
            }
            result.deleteCharAt(result.length() - 1);

            return result.toString();
        }
    }

    class TrieNode {
        TrieNode[] next;
        boolean isWord;
        TrieNode() {
            next = new TrieNode[26];
            isWord = false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
