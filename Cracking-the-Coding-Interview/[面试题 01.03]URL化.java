//URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，
//请使用字符数组实现，以便直接在数组上操作。） 
//
// 示例1: 
//
//  输入："Mr John Smith    ", 13
// 输出："Mr%20John%20Smith"
// 
//
// 示例2: 
//
//  输入："               ", 5
// 输出："%20%20%20%20%20"
// 
//
// 提示： 
//
// 
// 字符串长度在[0, 500000]范围内。 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * char Array的方式。
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces(String S, int length) {
        char[] strArr = S.toCharArray();
        for (int i = 0, j = 0; i < length; i++) {
            if (S.charAt(i) != ' ') {
                strArr[j++] = S.charAt(i);
            } else {
                strArr[j++] = '%';
                strArr[j++] = '2';
                strArr[j++] = '0';
            }
        }
        return (new String(strArr)).strip(); // 去除后面多余的空格
    }
}
//leetcode submit region end(Prohibit modification and deletion)
