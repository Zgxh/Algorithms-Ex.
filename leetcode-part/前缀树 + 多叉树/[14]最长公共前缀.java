//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串 
// 👍 1234 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路1：横向比较，先比较前两个，得到公共前缀，然后该前缀依次与后面的字符串
     *      进行比较，并更新前缀。
     * 思路2：纵向比较，按字符串的index依次进行比较，先比较1号位置，2号位置。。
     *      直到发现存在不同的字符的位置。
     * 思路3：分治。类似于归并排序的分治过程，每次把字符串数组分成两部分，然后递归
     *      分成两个子数组，在子数组中进行比较。然后把返回值进行比较并向上返回。
     * 思路4：字典树。类似于横向比较的思路。只不过是利用字典树进行字符匹配的判断。
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        } if (strs.length == 1) {
            return strs[0];
        }
        int len = strs.length;
        TrieNode root = new TrieNode();
        // 利用第一个单词建立字典树
        TrieNode temp = root;
        for (char ch : strs[0].toCharArray()) {
            if (temp.next[ch - 'a'] == null) {
                temp.next[ch - 'a'] = new TrieNode();
            }
            temp = temp.next[ch - 'a'];
            temp.count++;
        }
        // 利用第2~len-1个单词寻找重复的前缀
        for (int i = 1; i < len - 1; i++) {
            temp = root;
            for (char ch : strs[i].toCharArray()) {
                temp = temp.next[ch - 'a'];
                if (temp != null && temp.count == i) {
                    temp.count++;
                } else {
                    break;
                }
            }
        }
        // 利用最后一个单词来确定最终的前缀长度
        int result = 0;
        temp = root;
        for (char ch : strs[len - 1].toCharArray()) {
            temp = temp.next[ch - 'a'];
            if (temp != null && temp.count == len - 1) {
                result++;
            } else {
                break;
            }
        }

        return strs[0].substring(0, result);
    }
}
class TrieNode {
    TrieNode[] next;
    int count;
    TrieNode() {
        this.next = new TrieNode[26];
        count = 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
