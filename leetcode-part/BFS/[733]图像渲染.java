//有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。 
//
// 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。 
//
// 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方
//向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。 
//
// 最后返回经过上色渲染后的图像。 
//
// 示例 1: 
//
// 
//输入: 
//image = [[1,1,1],[1,1,0],[1,0,1]]
//sr = 1, sc = 1, newColor = 2
//输出: [[2,2,2],[2,2,0],[2,0,1]]
//解析: 
//在图像的正中间，(坐标(sr,sc)=(1,1)),
//在路径上所有符合条件的像素点的颜色都被更改成2。
//注意，右下角的像素没有更改为2，
//因为它不是在上下左右四个方向上与初始点相连的像素点。
// 
//
// 注意: 
//
// 
// image 和 image[0] 的长度在范围 [1, 50] 内。 
// 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。 
// image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。 
// 
// Related Topics 深度优先搜索 
// 👍 104 👎 0


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 思路：深度优先搜索 BFS
     * tip：代替使用visited数组来记录是否访问，采用在放入队列之后直接进行颜色的变更，
     * 这样下次就不会出现重复判断。
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image.length == 0 || image[0].length == 0 || image[sr][sc] == newColor) {
            return image;
        }
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        int rLen = image.length, cLen = image[0].length;
        int flagColor = image[sr][sc]; // 记录要被改变的颜色
        Queue<int[]> queue = new LinkedList(); // 存放像素点的坐标
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor; // 每次放入queue之后直接改变颜色，相当于实现了访问记录的功能
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            for (int i = 0;i < 4; i++) {
                sr = temp[0] + dr[i];
                sc = temp[1] + dc[i];
                if (sr >= 0 && sr < rLen && sc >= 0 && sc < cLen && image[sr][sc] == flagColor) {
                    queue.offer(new int[]{sr, sc});
                    image[sr][sc] = newColor;
                }
            }
        }

        return image;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
