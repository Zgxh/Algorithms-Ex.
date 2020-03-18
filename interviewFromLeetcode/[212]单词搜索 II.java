//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
// 
//
// 示例: 
//
// 输入: 
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"] 
//
// 说明: 
//你可以假设所有输入都由小写字母 a-z 组成。 
//
// 提示: 
//
// 
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？ 
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。 
// 
// Related Topics 字典树 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：与79不同，这题利用了前缀树[leetcode-208]。
     * 这回是遍历board的所有可能路径，然后判断是否匹配前缀树，效率大幅提升。
     * 设置类变量来防止函数形参过多。
     */

    private boolean[][] visited;
    private int iLen, jLen;
    WordTrie wordTrie = new WordTrie();
    Set<String> result = new HashSet<>(); // 防止字符串重复

    public List<String> findWords(char[][] board, String[] words) {

        if (board == null || board.length == 0 ||
                board[0] == null || board[0].length == 0 ||
                words == null || words.length == 0) {
            return new LinkedList<>();
        }

        iLen = board.length;
        jLen = board[0].length;
        visited = new boolean[iLen][jLen];

        for (String word : words) { // 建立字典树
            wordTrie.insert(word);
        }

        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                findWordsHelp(board, i, j, wordTrie.root);
            }
        }

        return new ArrayList<>(result);
    }

    public void findWordsHelp(char[][] board, int i, int j, TrieNode curNode) {

        if (visited[i][j] == true) { // 已经访问过这个结点,则结束
            return;
        }

        visited[i][j] = true;
        curNode = curNode.links[board[i][j] - 'a'];

        if (curNode == null) {
            visited[i][j] = false;
            return;
        }
        if (curNode.isLeaf) { // 叶子即为完整单词，但叶子也可能还有孩子，应该继续回溯
            result.add(curNode.val);
        }

        if (i > 0) {
            findWordsHelp(board, i - 1, j, curNode);
        }
        if (i < iLen - 1) {
            findWordsHelp(board, i + 1, j, curNode);
        }
        if (j > 0) {
            findWordsHelp(board, i, j - 1, curNode);
        }
        if (j < jLen - 1) {
            findWordsHelp(board, i, j + 1, curNode);
        }

        visited[i][j] = false; // 还原现场，这次不成，下种情况还有机会
    }

    // 字典树
    class WordTrie {

        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode currentNode = root;
            for (int i = 0; i < word.length(); i++) {
                if (currentNode.links[word.charAt(i) - 'a'] == null) {
                    currentNode.links[word.charAt(i) - 'a'] = new TrieNode();
                }
                currentNode = currentNode.links[word.charAt(i) - 'a'];
            }
            currentNode.isLeaf = true;
            currentNode.val = word;
        }
    }

    /**
     * 字典树的结点
     */
    class TrieNode {
        public String val;
        public TrieNode[] links; // 小写字母一共26个
        public boolean isLeaf = false;

        public TrieNode() {
            links = new TrieNode[26];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
