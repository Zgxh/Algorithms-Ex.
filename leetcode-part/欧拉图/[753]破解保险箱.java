//有一个需要密码才能打开的保险箱。密码是 n 位数, 密码的每一位是 k 位序列 0, 1, ..., k-1 中的一个 。 
//
// 你可以随意输入密码，保险箱会自动记住最后 n 位输入，如果匹配，则能够打开保险箱。 
//
// 举个例子，假设密码是 "345"，你可以输入 "012345" 来打开它，只是你输入了 6 个字符. 
//
// 请返回一个能打开保险箱的最短字符串。 
//
// 
//
// 示例1: 
//
// 输入: n = 1, k = 2
//输出: "01"
//说明: "10"也可以打开保险箱。
// 
//
// 
//
// 示例2: 
//
// 输入: n = 2, k = 2
//输出: "00110"
//说明: "01100", "10011", "11001" 也能打开保险箱。
// 
//
// 
//
// 提示： 
//
// 
// n 的范围是 [1, 4]。 
// k 的范围是 [1, 10]。 
// k^n 最大可能为 4096。 
// 
//
// 
// Related Topics 深度优先搜索 数学 
// 👍 66 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 欧拉回路
    // 密码有n位，把n-1位的密码看成一个顶点，这样每个n位长的字符串都包含两个顶点：
    // 即前n-1位和后n-1位
    Set<String> visited;
    StringBuilder result;

    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) {
            return "0";
        }
        this.visited = new HashSet();
        this.result = new StringBuilder();
        // 从顶点 00..00 开始寻找
        String start = "";
        for (int i = 1; i < n; i++) {
            start += "0";
        }
        findEuler(start, k);
        result.append(start);

        return result.toString();
    }

    private void findEuler(String cur, int k) {
        // 遍历当前顶点的每一条出边
        for (int i = 0; i < k; i++) {
            String next = cur + i;
            if (!visited.contains(next)) {
                // 先标记该出边已访问，然后递归访问该出边
                visited.add(next);
                findEuler(next.substring(1), k);
                result.append(i);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
