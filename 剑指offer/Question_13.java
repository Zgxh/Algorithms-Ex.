/**
 * 剑指offer第13题：调整数组顺序使奇数位于偶数前面
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 * @author Yu Yang
 * @create 2020-01-28 22:14
 */
public class Question_13 {

    /**
     * 有几个思路：
     * （1）利用冒泡的思想，把偶数往右边冒，每次交换相邻的奇数和偶数，时间复杂度O(n^2)，空间O(1)
     * （2）双指针，i指示从左往右第一个偶数，j指示i往右的第一个奇数，i、j之间的偶数右移一个位置，j指示的奇数插到i位置，时空同上。
     * （3）利用空间换时间，设置两个格外空间，分别存放奇数和偶数，最后再合并。时间O(n)，空间O(n)。
     * 下面的代码采用第三种方法实现。
     * @param array 待调整的数组，要在原数组上进行调整。
     */
    public void reOrderArray(int[] array) {
        if (array == null) {
            return;
        }
        int[] odd = new int[array.length];
        int[] even = new int[array.length];
        int i = 0, j = 0;
        for (int num : array) {
            if (num % 2 == 1) {
                odd[i++] = num;
            } else {
                even[j++] = num;
            }
        }

        for (int k = 0; k < i; k++) {
            array[k] = odd[k];
        }
        for (int k = i; k < array.length; k++) {
            array[k] = even[k - i];
        }
    }
}
