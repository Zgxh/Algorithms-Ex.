/**
 * 求矩阵的快速幂
 */
class Solution {

    /**
     * 求 方阵 a 的 n 次幂
     *
     * @param a 方阵 len * len
     * @param n 幂次
     * @return a 的 n 次幂
     */
    public int[][] pow(int[][] a, int n) {
        int len = a.length;
        int[][] ret = new int[len][len];
        for (int i = 0; i < len; i++) {
            ret[i][i] = 1;
        }
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }

        return ret;
    }

    /**
     * 两个矩阵的乘积矩阵
     */
    public int[][] multiply(int[][] a, int[][] b) {
        int len = a.length;
        int[][] c = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return c;
    }
}