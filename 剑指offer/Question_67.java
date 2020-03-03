import java.util.ArrayList;
import java.util.Arrays;

/**
 * 剑指offer第67题：剪绳子
 *
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），每段绳子的长度
 * 记为k[0],k[1],...,k[m-1]。请问k[0]xk[1]x...xk[m-1]可能的最大乘积是多少？例如，当绳子的长度
 * 是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * （2 <= n <= 60）
 *
 * @author Yu Yang
 * @create 2020-03-03 19:53
 */
public class Question_67 {

    /**
     * 思路：动态规划。用数组存放每个值的组合最大乘积，避免重复计算。
     * @param target
     * @return
     */
    public int cutRope(int target) {
        // 特殊情况，即俩数的乘积比自身更小
        if (target == 2) {
            return 1;
        } else if (target == 3) {
            return 2;
        } else if (target == 4) {
            return 4;
        }
        // 正常情况
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,1,2,3,4));
        for (int i = 5; i <= target; i++) {
            int max = i;
            for (int j = 2; j <= i / 2; j++) {
                int temp = list.get(j) * list.get(i - j);
                max = temp > max ? temp : max;
            }
            list.add(max);
        }
        return list.get(target);
    }
}
