class Solution_50 {
    public double myPow(double x, int n) {
        double result = 1.0;
        boolean negative = n < 0;
        if (negative) 
            n = -n;
        for (int i=n; i!=0; i=i/2) {
            if (i % 2 != 0) {
                result *= x;
            }
            x = x * x;
        }
        return negative ? 1 / result : result;
    }
}