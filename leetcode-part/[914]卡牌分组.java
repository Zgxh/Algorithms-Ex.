//给定一副牌，每张牌上都写着一个整数。 
//
// 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组： 
//
// 
// 每组都有 X 张牌。 
// 组内所有的牌上都写着相同的整数。 
// 
//
// 仅当你可选的 X >= 2 时返回 true。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,4,4,3,2,1]
//输出：true
//解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
// 
//
// 示例 2： 
//
// 输入：[1,1,1,2,2,2,3,3]
//输出：false
//解释：没有满足要求的分组。
// 
//
// 示例 3： 
//
// 输入：[1]
//输出：false
//解释：没有满足要求的分组。
// 
//
// 示例 4： 
//
// 输入：[1,1]
//输出：true
//解释：可行的分组是 [1,1]
// 
//
// 示例 5： 
//
// 输入：[1,1,2,2,2,2]
//输出：true
//解释：可行的分组是 [1,1]，[2,2]，[2,2]
// 
//
// 
//提示： 
//
// 
// 1 <= deck.length <= 10000 
// 0 <= deck[i] < 10000 
// 
//
// 
// Related Topics 数组 数学


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：hashmap先统计各个元素的个数，然后不断求gcd，如果gcd是1了，则不行。
     *
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {

        if (deck == null || deck.length <= 1) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int minCount = Integer.MAX_VALUE;

        for (int num : deck) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Iterator<Integer> iterator = map.values().iterator();
        minCount = iterator.next();
        if (!iterator.hasNext()) { // 针对map中只有一个entry的情况
            return minCount >= 2 ? true : false;
        }

        while (iterator.hasNext()) {
            int count = iterator.next();
            int gcd = gcd(count, minCount);
            if (gcd == 1) {
                return false;
            }
            minCount = gcd;
        }
        return true;
    }

    /**
     * 辗转相除法求最大公约数。
     * @param x
     * @param y
     * @return
     */
    private int gcd(int x, int y) {
        int remainder = 0;
        while (y != 0) {
            remainder = x % y;
            x = y;
            y = remainder;
        }
        return x;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
