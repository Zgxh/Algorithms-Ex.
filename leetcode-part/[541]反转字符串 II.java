//给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于
// 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。 
//
// 示例: 
//
// 
//输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 要求: 
//
// 
// 该字符串只包含小写的英文字母。 
// 给定字符串的长度和 k 在[1, 10000]范围内。 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 分情况讨论，剩下的块小于k，和大于k。
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {

        char[] arr = s.toCharArray();
        int len = s.length();

        for (int i = 0; i < len; i += 2 * k) {
            if (i + k - 1 >= len) {
                reverse(arr, i, len - 1);
            } else {
                reverse(arr, i, i + k - 1);
            }
        }

        return new String(arr);
    }

    private void reverse(char[] arr, int low, int high) {

        char temp;

        for (int i = low, j = high; i < low + (high - low + 1) / 2; i++, j--) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
