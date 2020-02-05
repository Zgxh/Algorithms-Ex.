/**
 * 剑指offer第30题：连续子数组的最大和
 *
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:
 * 在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
 * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},
 * 连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 *
 * @author Yu Yang
 * @create 2020-02-06 0:11
 */
public class Question_30 {

    /**
     * 思路：利用辅助数组，存放每个元素的最大和，当遍历到一个元素时，如果前面的部分不利于和的增长，
     * 则舍弃前面的部分，否则就一直加。
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] sum = new int[array.length + 1];
        int max = array[0];
        for (int i = 1; i < sum.length; i++) {
            int temp = sum[i - 1] + array[i - 1];
            sum[i] = temp > array[i - 1] ? temp : array[i - 1];
            if (sum[i] > max) {
                max = sum[i];
            }
        }
        return max;
    }
}
