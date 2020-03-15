class Solution_3 {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int nowLength;
        int len = s.length();
        if (len == 1) return 1;
        char[] str = s.toCharArray();
        int begin = 0;
        int end = 0;
        for (int i=1; i<len; i++) {
            end = i;
            nowLength = end - begin + 1;
            for (int j=begin; j<end; j++) {
                if (str[i] == str[j]) {
                    end = i - 1;
                    nowLength = end - begin + 1;
                    begin = j + 1;
                    end = i;
                    break;
                }
            }
            if (maxLength < nowLength) maxLength = nowLength;
        }
        return maxLength;
    }
}