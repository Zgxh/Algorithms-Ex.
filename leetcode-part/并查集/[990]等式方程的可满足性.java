//给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!
//=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。 
//
// 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：["a==b","b!=a"]
//输出：false
//解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
// 
//
// 示例 2： 
//
// 输出：["b==a","a==b"]
//输入：true
//解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
// 
//
// 示例 3： 
//
// 输入：["a==b","b==c","a==c"]
//输出：true
// 
//
// 示例 4： 
//
// 输入：["a==b","b!=c","c==a"]
//输出：false
// 
//
// 示例 5： 
//
// 输入：["c==c","b==d","x!=z"]
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= equations.length <= 500 
// equations[i].length == 4 
// equations[i][0] 和 equations[i][3] 是小写字母 
// equations[i][1] 要么是 '='，要么是 '!' 
// equations[i][2] 是 '=' 
// 
// Related Topics 并查集 图


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：并查集。
     * 首先，遇到这种key值是有限的情况，尤其是全是字母，先考虑【定长数组】。
     * 并查集的做法是通过parent[]数组来存储每个变量的父节点，每个连通分量的根结点对应的index是他本身，
     * 然后同一个连通分量拥有相同的根结点；
     * 并查集涉及到的操作有连通分量的合并：首先找到两个连通分量分别的根结点，然后让其中一个的根等于另一个的根。
     */

    int[] parent;

    public boolean equationsPossible(String[] equations) {
        parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // 第一遍遍历==的情况，建立连通分量
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                union(index1, index2);
            }
        }

        // 第二遍遍历!=的情况，找不符合的结点
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                if (findParent(index1) == findParent(index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void union(int index1, int index2) {
        parent[findParent(index1)] = findParent(index2);
    }

    private int findParent(int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]]; // 在找根结点的同时降低连通分量树的高度
            index = parent[index];
        }
        return index;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
