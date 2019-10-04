class Solution_69 {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1;
        int right = x;
        while (right > left + 1) {
            int mid = left + (right - left) / 2;
            int temp = x / mid;
            if (mid == temp) {
                return mid;
            }
            else if (mid > temp) {
                right = mid;
            }
            else {
                left = mid;
            }
        }
        return left;
    }
}