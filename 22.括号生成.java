/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        list.add("()");
        for (int i=1; i<n; i++) {
            int size = list.size();
            for (int j=0; j<size; j++) {
                for (int k=0; k<2*i; k++) {
                    StringBuilder strb = new StringBuilder("");
                    strb.append(list.get(0));
                    strb.insert(k, '(');
                    strb.insert(k+1, ')');
                    if (!list.contains(strb.toString())) {
                        list.add(strb.toString());
                    }
                }
                list.remove(0);
            }
        }
        return list;
    }
}

