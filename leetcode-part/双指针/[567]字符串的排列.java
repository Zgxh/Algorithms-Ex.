//给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。 
//
// 换句话说，第一个字符串的排列之一是第二个字符串的子串。 
//
// 示例1: 
//
// 
//输入: s1 = "ab" s2 = "eidbaooo"
//输出: True
//解释: s2 包含 s1 的排列之一 ("ba").
// 
//
// 
//
// 示例2: 
//
// 
//输入: s1= "ab" s2 = "eidboaoo"
//输出: False
// 
//
// 
//
// 注意： 
//
// 
// 输入的字符串只包含小写字母 
// 两个字符串的长度都在 [1, 10,000] 之间 
// 
// Related Topics 双指针 Sliding Window 
// 👍 263 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 双指针法：检查s2中与s1长度相等的子串是否含有相同数目的字符统计
    public boolean checkInclusion(String s1, String s2) {
        int left = 0, right = 0;
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int[] count1 = new int[26];
        // 为了避免每次都遍历count1来确定是否全部位置为0，设置distance来记录非零字符的个数
        int distance = 0;
        for (char ch : s1.toCharArray()) {
            if (count1[ch - 'a'] == 0) {
                distance++;
            }
            count1[ch - 'a']++;
        }
        // 初始化前 len1 长度
        while (right < len1) {
            char ch = s2.charAt(right);
            count1[ch - 'a']--;
            if (count1[ch - 'a'] == 0) {
                distance--;
            } else if (count1[ch - 'a'] == -1) {
                distance++;
            }
            right++;
        }
        if (distance == 0) {
            return true;
        }
        while (right < len2) {
            char ch = s2.charAt(right);
            count1[ch - 'a']--;
            if (count1[ch - 'a'] == 0) {
                distance--;
            } else if (count1[ch - 'a'] == -1) {
                distance++;
            }
            right++;
            char leftCh = s2.charAt(left);
            count1[leftCh - 'a']++;
            if (count1[leftCh - 'a'] == 0) {
                distance--;
            } else if (count1[leftCh - 'a'] == 1) {
                distance++;
            }
            left++;
            if (distance == 0) {
                return true;
            }
        }

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
