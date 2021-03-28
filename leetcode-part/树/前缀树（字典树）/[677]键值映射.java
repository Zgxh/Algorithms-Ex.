//实现一个 MapSum 类里的两个方法，insert 和 sum。 
//
// 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。 
//
// 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。 
//
// 示例 1: 
//
// 输入: insert("apple", 3), 输出: Null
//输入: sum("ap"), 输出: 3
//输入: insert("app", 2), 输出: Null
//输入: sum("ap"), 输出: 5
// 
// Related Topics 字典树 
// 👍 49 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class MapSum {

    private TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        int previous = getVal(key); // 获取之前的val,而不是prefixSum
        int diff = val - previous; // key之前的结点的sum值要变动
        TrieNode pointer = root;
        pointer.prefixSum += diff;
        for (char ch : key.toCharArray()) {
            if (pointer.next[ch - 'a'] == null) {
                pointer.next[ch - 'a'] = new TrieNode();
            }
            pointer = pointer.next[ch - 'a'];
            pointer.prefixSum += diff;
        }
        pointer.val = val;
    }

    private int getVal(String key) {
        TrieNode pointer = root;
        for (char ch : key.toCharArray()) {
            if (pointer.next[ch - 'a'] == null) {
                return 0;
            }
            pointer = pointer.next[ch - 'a'];
        }

        return pointer.val;
    }

    public int sum(String prefix) {
        TrieNode pointer = root;
        for (char ch : prefix.toCharArray()) {
            if (pointer.next[ch - 'a'] == null) {
                return 0;
            }
            pointer = pointer.next[ch - 'a'];
        }

        return pointer.prefixSum;
    }
}

class TrieNode {
    TrieNode[] next;
    int val;
    int prefixSum;
    TrieNode() {
        next = new TrieNode[26];
        val = 0;
        prefixSum = 0;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
