/**
 * 剑指offer第33题：丑数
 *
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，
 * 因为它包含质因子7。 习惯上我们把1当做是第一个丑数。
 * 求按从小到大的顺序的第N个丑数。
 *
 * 这道题对应leetcode的264题
 *
 * @author Yu Yang
 * @create 2020-02-12 20:35
 */
public class Question_33 {

    /**
     * 思路：采用三指针，分别指向即将被2、3、5相乘的数，每次三指针上的数与对应的乘子相乘，得到下一个最小丑数，
     * 放入数组中，直到获得足够多的丑数。
     * @param n
     * @return
     */
    public int GetUglyNumber_Solution(int n) {
        if (n <= 0) {
            return 0;
        }
        int indexOf2 = 0, indexOf3 = 0, indexOf5 = 0;
        int[] uglyArray = new int[n];
        uglyArray[0] = 1;
        for (int i = 1; i < n; i++) {
            int currentMin = Math.min(Math.min(uglyArray[indexOf2] * 2, uglyArray[indexOf3] * 3), uglyArray[indexOf5] * 5);
            uglyArray[i] = currentMin;
            if (currentMin == uglyArray[indexOf2] * 2) {
                indexOf2++;
            }
            if (currentMin == uglyArray[indexOf3] * 3) {
                indexOf3++;
            }
            if (currentMin == uglyArray[indexOf5] * 5) {
                indexOf5++;
            }
        }
        return uglyArray[n - 1];
    }
}
