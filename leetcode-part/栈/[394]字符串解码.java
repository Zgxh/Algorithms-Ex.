//给定一个经过编码的字符串，返回它解码后的字符串。 
//
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。 
//
// 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。 
//
// 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。 
//
// 示例: 
//
// 
//s = "3[a]2[bc]", 返回 "aaabcbc".
//s = "3[a2[c]]", 返回 "accaccacc".
//s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
// 
// Related Topics 栈 深度优先搜索


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 栈
    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        String temp;
        for (char ch : s.toCharArray()) {
            if (ch != ']') {
                stack.push(ch + "");
            } else {
                // 确定重复的子序列
                StringBuilder single = new StringBuilder();
                while (!(temp = stack.pop()).equals("[")) {
                    single.insert(0, temp);
                }
                // 确定重复次数
                int j = 0;
                int iteration = 0;
                while (!stack.isEmpty() && stack.peek().matches("[0-9]")) {
                    iteration += Integer.parseInt(stack.pop()) * Math.pow(10, j++);
                }
                // 求得重复子串
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < iteration; i++) {
                    sb.append(single);
                }
                stack.push(sb.toString());
            }
        }
        StringBuilder result = new StringBuilder();
        for (String str : stack) {
            result.insert(0, str);
        }

        return result.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
