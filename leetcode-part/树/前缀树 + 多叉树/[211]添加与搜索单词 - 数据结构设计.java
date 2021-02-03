//设计一个支持以下两种操作的数据结构： 
//
// void addWord(word)
//bool search(word)
// 
//
// search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。 
//
// 示例: 
//
// addWord("bad")
//addWord("dad")
//addWord("mad")
//search("pad") -> false
//search("bad") -> true
//search(".ad") -> true
//search("b..") -> true
// 
//
// 说明: 
//
// 你可以假设所有单词都是由小写字母 a-z 组成的。 
// Related Topics 设计 字典树 回溯算法 
// 👍 146 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class WordDictionary {

    /**
     * 思路：前缀树
     *
     */

    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) { // 建树
        TrieNode pointer = root;
        for (char ch : word.toCharArray()) {
            if (pointer.next[ch - 'a'] == null) {
                pointer.next[ch - 'a'] = new TrieNode();
            }
            pointer = pointer.next[ch - 'a'];
        }
        pointer.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchHelp(word, 0, root);
    }

    private boolean searchHelp(String word, int index, TrieNode pointer) {
        if (index == word.length() - 1) {
            if (word.charAt(index) == '.') {
                boolean canSearch = false;
                for (int i = 0; i < 26; i++) {
                    if (pointer.next[i] != null) {
                        canSearch = canSearch || pointer.next[i].isWord;
                    }
                }
                return canSearch;
            } else {
                return pointer.next[word.charAt(index) - 'a'] != null && pointer.next[word.charAt(index) - 'a'].isWord;
            }
        } else {
            if (word.charAt(index) == '.') {
                boolean canSearch = false;
                for (int i = 0; i < 26; i++) {
                    if (pointer.next[i] != null) {
                        canSearch = canSearch || searchHelp(word, index + 1, pointer.next[i]);
                    }
                }
                return canSearch;
            } else {
                if (pointer.next[word.charAt(index) - 'a'] == null) {
                    return false;
                }
                return searchHelp(word, index + 1, pointer.next[word.charAt(index) - 'a']);
            }
        }
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

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)
