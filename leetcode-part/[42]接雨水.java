//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 动态规划。分别求出当前index左边的maxHeight和右边的maxHeight。然后直接计算总雨水体积。
     * dp方程：maxLeft[i] = max(maxLeft[i-1], height[i-1]);
     *        maxRight[i] = max(maxRight[i+1], height[i+1]).
     * 时间O(n)，空间O(n).
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        //构建dp数组
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        // 累加结果
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            int minHeight = Math.min(maxLeft[i], maxRight[i]);
            if (minHeight > height[i]) {
                result += minHeight - height[i] ;
            }
        }

        return result;
    }

    /**
     * 【改进：双指针法】：把dp数组改成2个int值，只存放当前位置的maxLeft和maxRight，
     * 但是由于之前的maxRIght是从右往左遍历过来的，现在就需要改变一下思路。
     * minHeight = Math.min(maxLeft, maxRight)总高度由左右最大值的min决定。
     * 而maxLeft由height[left-1]更新而来，maxRight由height[right+1]更新而来。
     * 主要思路：每次碰到大的maxHeight了，则算小的一边，因为接的雨水由小的决定。即每次都更新小的一侧。
     * 换边的时候即为maxLeft和maxRight的最小关系要变更的时候。
     */
    public int trap1(int[] height) {

        int result = 0;
        int maxLeft = 0;
        int maxRight = 0;

        int left = 1;
        int right = height.length - 2;

        for (int i = 1; i < height.length - 1; i++) {
            if (height[left - 1] < height[right + 1]) { // 此时的maxLeft更小
                maxLeft = Math.max(maxLeft, height[left - 1]);
                if (maxLeft > height[left]) {
                    result = result + (maxLeft - height[left]);
                }
                left++;
            } else { // 此时的maxRight更小
                maxRight = Math.max(maxRight, height[right + 1]);
                if (maxRight > height[right]) {
                    result = result + (maxRight - height[right]);
                }
                right--;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
