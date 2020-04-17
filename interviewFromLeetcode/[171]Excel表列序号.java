//给定一个Excel表格中的列名称，返回其相应的列序号。 
//
// 例如， 
//
//     A -> 1
//    B -> 2
//    C -> 3
//    ...
//    Z -> 26
//    AA -> 27
//    AB -> 28 
//    ...
// 
//
// 示例 1: 
//
// 输入: "A"
//输出: 1
// 
//
// 示例 2: 
//
// 输入: "AB"
//输出: 28
// 
//
// 示例 3: 
//
// 输入: "ZY"
//输出: 701 
//
// 致谢： 
//特别感谢 @ts 添加此问题并创建所有测试用例。 
// Related Topics 数学


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 相当于是计算26进制数。不过没有0.是1~26.
     * @param s
     * @return
     */
    public int titleToNumber(String s) {

        int result = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            result += Math.pow(26, len - 1 - i) * (s.charAt(i) - 64); // 'A'对应的值是65
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
