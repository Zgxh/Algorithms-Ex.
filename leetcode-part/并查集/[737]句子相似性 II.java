//给定两个句子 words1, words2 （每个用字符串数组表示），和一个相似单词对的列表 pairs ，判断是否两个句子是相似的。 
//
// 例如，当相似单词对是 pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"
//]]的时候，words1 = ["great", "acting", "skills"] 和 words2 = ["fine", "drama", "talen
//t"] 是相似的。 
//
// 注意相似关系是 具有 传递性的。例如，如果 "great" 和 "fine" 是相似的，"fine" 和 "good" 是相似的，则 "great" 和 
//"good" 是相似的。 
//
// 而且，相似关系是具有对称性的。例如，"great" 和 "fine" 是相似的相当于 "fine" 和 "great" 是相似的。 
//
// 并且，一个单词总是与其自身相似。例如，句子 words1 = ["great"], words2 = ["great"], pairs = [] 是相似的
//，尽管没有输入特定的相似单词对。 
//
// 最后，句子只会在具有相同单词个数的前提下才会相似。所以一个句子 words1 = ["great"] 永远不可能和句子 words2 = ["double
//plus","good"] 相似。 
//
// 注： 
//
// 
// words1 and words2 的长度不会超过 1000。 
// pairs 的长度不会超过 2000。 
// 每个pairs[i] 的长度为 2。 
// 每个 words[i] 和 pairs[i][j] 的长度范围为 [1, 20]。 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 31 👎 0


import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 来了结点，首先把自己跟自己对应放进去，放入前先判断集合中有没有该结点，如果没有再放入
    // 放入两个结点后，执行合并

    private Map<String, String> parents = new HashMap();

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        // 所有拥有相同的根的元素都是相似的
        int len = words1.length;
        for (List<String> pair : pairs) {
            String child = pair.get(0), parent = pair.get(1);
            if (!parents.containsKey(child)) {
                parents.put(child, child);
            }
            if (!parents.containsKey(parent)) {
                parents.put(parent, parent);
            }
            union(child, parent);
        }
        for (int i = 0; i < len; i++) {
            String node1 = words1[i], node2 = words2[i];
            if (node1.equals(node2)) {
                continue;
            }
            String root1 = find(node1), root2 = find(node2);
            if (!root1.equals(root2)) {
                return false;
            }
        }

        return true;
    }

    private void union(String node1, String node2) {
        String root1 = find(node1), root2 = find(node2);
        if (!root1.equals(root2)) {
            parents.put(root1, root2);
        }
    }

    private String find(String node) {
        String parent = parents.get(node);
        // 可能pairs中没有覆盖word1和word2中的元素
        if (parent == null) {
            return node;
        }
        if (!node.equals(parent)) {
            parents.put(node, find(parent));
        }

        return parents.get(node);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
