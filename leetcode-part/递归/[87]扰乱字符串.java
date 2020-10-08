//给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。 
//
// 下图是字符串 s1 = "great" 的一种可能的表示形式。 
//
//     great
//   /    \
//  gr    eat
// / \    /  \
//g   r  e   at
//           / \
//          a   t
// 
//
// 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。 
//
// 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。 
//
//     rgeat
//   /    \
//  rg    eat
// / \    /  \
//r   g  e   at
//           / \
//          a   t
// 
//
// 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。 
//
// 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。 
//
//     rgtae
//   /    \
//  rg    tae
// / \    /  \
//r   g  ta  e
//       / \
//      t   a
// 
//
// 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。 
//
// 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。 
//
// 示例 1: 
//
// 输入: s1 = "great", s2 = "rgeat"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s1 = "abcde", s2 = "caebd"
//输出: false 
// Related Topics 字符串 动态规划 
// 👍 155 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     // 递归写法
     public boolean isScramble(String s1, String s2) {
         if (s1.length() != s2.length()) {
             return false;
         }
         if (s1.equals(s2)) {
             return true;
         }
         int len = s1.length();
         // 统计s1与s2中的字符出现的次数，进行预判断
         HashMap<Character, Integer> charCount = new HashMap();
         for (char ch : s1.toCharArray()) {
             charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
         }
         for (char ch : s2.toCharArray()) {
             charCount.put(ch, charCount.getOrDefault(ch, 0) - 1);
         }
         for (int count : charCount.values()) {
             if (count != 0) {
                 return false;
             }
         }
         // 递归判断两个子序列是否匹配：因为可以交换或不交换，所以有两种情况，满足任一种即可
         // （1）s1的前段匹配s2的前段；（2）s1的前段匹配s2的后段
         for (int i = 1; i < len; i++) {
             boolean scramble = isScramble(s1.substring(0, i), s2.substring(0, i))
                 && isScramble(s1.substring(i), s2.substring(i))
                 || isScramble(s1.substring(0, i), s2.substring(len - i))
                 && isScramble(s1.substring(i), s2.substring(0, len - i));
             if (scramble) {
                 return true;
             }
         }

         return false;
     }
}
//leetcode submit region end(Prohibit modification and deletion)
