import java.util.LinkedList;
//快乐就完事了

class Solution {
    public boolean isHappy(int n) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        while (true) {
            int temp = 0;
            while (n != 0) {
                temp += Math.pow(n%10, 2);
                n = n / 10;
            }
            n = temp;
            if (list.contains(n))   return false;
            list.add(n);
            if (n == 1) return true;
        }
    }
}