//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。 
//
// 
//
// 示例 1: 
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi" 
//
// 
//
// 提示： 
//
// 
// 0 <= num < 231 
// 
//


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 简单的动态规划问题。
     * 当前字符串的翻译与前两个位置有关，要么就是i-2加2个字符，要么就是i-1加一个字符。
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String numStr = String.valueOf(num);
        int first = 1, second = 1;
        int temp;
        for (int i = 1; i < numStr.length(); i++) {
            temp = second;
            if (numStr.charAt(i - 1) != '0' && // 0的时候要排除
                    Integer.parseInt(numStr.substring(i - 1, i + 1)) <= 25) {
                second = first + second;
            }
            first = temp;
        }

        return second;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
