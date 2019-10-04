/*
 * @lc app=leetcode.cn id=118 lang=java
 *
 * [118] 杨辉三角
 */
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> listOut = new ArrayList<List<Integer>>();
        if (numRows == 0) return listOut;
        List<Integer> listIn1 = new ArrayList<Integer>();
        listIn1.add(1);
        listOut.add(listIn1);
        for (int i=1; i<numRows; i++) {
            List<Integer> listIn = new ArrayList<Integer>();
            listIn.add(1);
            listIn.add(1);
            for (int j=1; j<i; j++) {
                listIn.add(j, listOut.get(i-1).get(j-1) + listOut.get(i-1).get(j));
            }
            listOut.add(listIn);
        }
        return listOut;
    }
}

