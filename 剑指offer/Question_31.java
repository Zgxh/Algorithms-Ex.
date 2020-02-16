/**
 * 剑指offer第31题：整数中1出现的次数
 *
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中
 * 包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮
 * 帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 *
 * @author Yu Yang
 * @create 2020-02-06 15:40
 */
public class Question_31 {

    /**
     * 思路1：遍历1~n，对每个数不断除10取余，计算1的个数。时间复杂度：O(n logn)
     * 思路2：
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }
}
