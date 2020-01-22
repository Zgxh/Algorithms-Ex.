/**
 * 剑指offer第一题：二维数组中的查找
 *
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 *
 * @author Yu Yang
 * @create 2020-01-22 20:38
 */
public class Question_1 {

    /**
     * 思路：从左下角开始搜索，大于target，横坐标 -1；小于target，纵坐标 +1。
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        if (array == null || array[0] == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int i = array.length - 1;
        int j = 0;
        while (i >= 0 && j < array[0].length) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }
}
