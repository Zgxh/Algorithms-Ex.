//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 
//
// 返回 s 所有可能的分割方案。 
//
// 示例: 
//
// 输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//] 
// Related Topics 回溯算法


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> partition(String s) {
        recursion(s, new ArrayList<String>(), 0);
        return result;
    }

    public void recursion(String s, List<String> ls, int begin) {
        if (begin == s.length()) {
            result.add(new ArrayList<>(ls));
            return;
        }
        for (int i = begin; i < s.length(); i++) {
            if (isPalindrome(s, begin, i)) {
                ls.add(s.substring(begin, i + 1));
                recursion(s, ls, i + 1);
                ls.remove(ls.size() - 1);
            }
        }
    }

    /**
     * judge whether a given string is a palindrome.
     * @param s
     * @param left
     * @param right
     * @return
     */
    public boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
