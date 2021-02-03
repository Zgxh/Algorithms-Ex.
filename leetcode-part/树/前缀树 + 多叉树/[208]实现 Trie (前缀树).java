//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 367 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Trie {

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode pointer = root;
        for (char ch : word.toCharArray()) {
            if (pointer.next[ch - 'a'] == null) {
                pointer.next[ch - 'a'] = new TrieNode();
            }
            pointer = pointer.next[ch - 'a'];
        }
        pointer.isLeaf = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode pointer = root;
        for (char ch : word.toCharArray()) {
            if (pointer.next[ch - 'a'] == null) {
                return false;
            }
            pointer = pointer.next[ch - 'a'];
        }

        if (pointer.isLeaf == false) {
            return false;
        } else {
            return true;
        }
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode pointer = root;
        for (char ch : prefix.toCharArray()) {
            if (pointer.next[ch - 'a'] == null) {
                return false;
            }
            pointer = pointer.next[ch - 'a'];
        }

        return true;
    }
}

class TrieNode {
    TrieNode[] next;
    boolean isLeaf = false;
    TrieNode() {
        next = new TrieNode[26];
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
