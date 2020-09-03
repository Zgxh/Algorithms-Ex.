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
// 👍 1611 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * DP。
     * 每个位置的最高水位是由左右的最高高度LeftHighest和RightHighest
     * 中的小者决定的。
     * 利用两个DP数组，分别存放每个点左右的最高高度。
     * 然后计算每个位置的水容量。
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }
        int[] rightHighest = new int[len];
        int[] leftHighest = new int[len];
        rightHighest[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightHighest[i] = Math.max(height[i], rightHighest[i + 1]);
        }
        leftHighest[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftHighest[i] = Math.max(height[i], leftHighest[i - 1]);
        }
        int result = 0;
        for (int i = 0; i < len; i++) { // 注意水位可能小于掩体高度，别减多了
            int waterLevel = Math.min(rightHighest[i], leftHighest[i]);
            result += waterLevel - Math.min(waterLevel, height[i]);
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
