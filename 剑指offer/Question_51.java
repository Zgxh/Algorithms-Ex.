/**
 * 剑指offer第51题：构建乘积数组
 *
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。
 * （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]；
 * B[n-1] = A[0] * A[1] * ... * A[n-2];）
 *
 * @author Yu Yang
 * @create 2020-02-25 10:35
 */
public class Question_51 {

    /**
     * 思路：用空间换时间。把B每个数的计算分成左右两部分left\right，分别先求left和right数组，然后按规则相乘。
     * 时间O(n)，空间O(n)
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int len = A.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int[] result = new int[len];
        left[0] = A[0];
        right[len - 1] = A[len - 1];
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * A[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * A[i];
        }
        result[0] = right[1];
        result[len - 1] = left[len - 2];
        for (int i = 1; i <= len - 2; i++) {
            result[i] = left[i - 1] * right[i + 1];
        }
        return result;
    }
}
