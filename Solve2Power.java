/*
*给定一个int型整数，返回不小于它本身的最小的2的平方数。
*/

public class Solve2Power {
    public static int solve(int input) {
        if (input == 0) return 0;
        int num = input > 0 ?  input - 1 : - input;
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        return input > 0 ? num + 1 : - ((num >>> 1) + 1);
    }
}