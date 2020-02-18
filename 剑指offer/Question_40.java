/**
 * 剑指offer第40题：数组中只出现一次的数字
 *
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 *
 * @author Yu Yang
 * @create 2020-02-18 13:10
 */
public class Question_40 {

    /**
     * 思路1：使用Map的额外空间，遍历一遍数组，记录次数，然后找到只出现1次的数字。
     * 思路2：与1个只出现一次的数字类似，使用异或。但本题是2个：先把数组异或一遍，
     * 得到的结果是不同的数A、B异或的结果，其中结果的二进制中1代表A、B在该位不同；
     * 按照该位的二进制，把数组分成两个子数组，然后分别再异或一遍，分别能得到A和B。
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int[] array,int[] num1 , int[] num2) {
        if (array == null || array.length == 0) {
            return;
        }
        int xor = 0;
        for (int num : array) {
            xor ^= num;
        }
        int judgeBit = 1;
        while ((judgeBit & xor) == 0) {     //判断哪一位不同
            judgeBit <<= 1;
        }
        int resultA = 0;
        int resultB = 0;
        for (int num : array) {
            if ((judgeBit & num) == 0) {
                resultA ^= num;
            } else {
                resultB ^= num;
            }
        }
        num1[0] = resultA;
        num2[0] = resultB;
    }
}
