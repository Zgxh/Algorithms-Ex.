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


//leetcode submit region begin(Prohibit modification and deletion)
class Trie {

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!currentNode.containsKey(currentChar)) {
                currentNode.put(currentChar, new TrieNode());
            }
            currentNode = currentNode.get(currentChar);
        }
        currentNode.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode trieNode = searchPrefix(word);
        return trieNode != null && trieNode.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    /**
     * 找到以prefix为前缀的字符串的孩子
     * @param prefix 前缀字符串
     * @return 不存在符合要求的返回null，存在的话返回孩子
     */
    private TrieNode searchPrefix(String prefix) {
        TrieNode currentNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            if (!currentNode.containsKey(currentChar)) {
                return null;
            }
            currentNode = currentNode.get(currentChar);
        }
        return currentNode;
    }

    /**
     * 定义前缀树的结点
     */
    class TrieNode {

        private TrieNode[] links; // 指向孩子数组

        private final int R = 26; // a-z共26个小写字母

        private boolean isEnd = false;

        // 空构造器
        public TrieNode() {
            this.links = new TrieNode[R];
        }

        // 判断当前结点有没有ch孩子
        public boolean containsKey(char ch) {
            return this.links[ch - 'a'] != null;
        }

        // 获得第ch个孩子的指针
        public TrieNode get(char ch) {
            return this.links[ch - 'a'];
        }

        // 添加第ch个孩子
        public void put(char ch, TrieNode trieNode) {
            this.links[ch - 'a'] = trieNode;
        }

        // 设置叶子标志
        public void setEnd() {
            this.isEnd = true;
        }

        public boolean isEnd() {
            return this.isEnd;
        }
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
