class Solution_125 {
    public boolean isPalindrome(String s) {
        if(s == null)   return true;
        s = s.toLowerCase();
        int len = s.length();
        StringBuilder strb = new StringBuilder(len);
        char[] str = s.toCharArray();
        for(int i=0; i<len; i++) {
            if((str[i]>='0' && str[i]<='9') || (str[i]>='a' && str[i]<='z'))
                strb.append(str[i]);
        }
        return strb.toString().equals(strb.reverse().toString());
    }
}


