import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 剑指offer第29题：最小的K个数
 *
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 *
 * @author Yu Yang
 * @create 2020-02-05 17:27
 */
public class Question_29 {

    /**
     * 思路1：先排序，然后找最小的K个数。
     * 思路2：维护一个存K个数的大顶堆，对input遍历一遍，每遇到比堆顶大的就替换掉堆顶。
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input == null || input.length == 0 || k == 0 || k > input.length) {
            return new ArrayList<>();
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 默认为小顶堆，改成大顶堆
        for (int num : input) {
            if (maxHeap.size() < k) {
                maxHeap.add(num);
            } else {
                if (num < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.add(num);
                }
            }
        }
        return new ArrayList<>(maxHeap);
    }
}
