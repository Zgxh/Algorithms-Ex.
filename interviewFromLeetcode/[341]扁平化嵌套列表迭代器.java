//给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。 
//
// 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。 
//
// 
//
// 示例 1: 
//
// 输入: [[1,1],2,[1,1]]
//输出: [1,1,2,1,1]
//解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。 
//
// 示例 2: 
//
// 输入: [1,[4,[6]]]
//输出: [1,4,6]
//解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
// 
// Related Topics 栈 设计


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    /**
     * next() 和 hasNext() 是迭代器的两个最重要的方法。
     * NestedIterator采用的方式是在初始化阶段就使用toIntegerList()方法
     * 把输入的nestedList转化为integerList，然后采用普通迭代器的方式进行迭代。
     */

    private List<Integer> list;
    private Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = toIntegerList(nestedList);
        iterator = list.iterator();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * 把List<NestedInteger>递归转化为List<Integer>
     *
     * @param nestedList
     * @return
     */
    private List<Integer> toIntegerList(List<NestedInteger> nestedList) {

        List<Integer> integerList = new ArrayList<>();

        for (NestedInteger listNode : nestedList) {
            if (listNode.isInteger()) {
                integerList.add(listNode.getInteger());
            } else {
                integerList.addAll(toIntegerList(listNode.getList()));
            }
        }

        return integerList;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
//leetcode submit region end(Prohibit modification and deletion)
