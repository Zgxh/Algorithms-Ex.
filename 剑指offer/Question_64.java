import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 剑指offer第64题：滑动窗口的最大值
 *
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么
 * 一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对
 * 数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}，
 * {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 *
 * @author Yu Yang
 * @create 2020-03-02 18:04
 */
public class Question_64 {

    /**
     * 思路：用大顶堆，每次弹出size的第一个，再加入下一个，maxHeap记录当前size个数据的最大值，每次把最大值放入结果数组中。
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (num == null || num.length == 0 || size <= 0 || size > num.length) {
            return result;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < size; i++) {
            maxHeap.add(num[i]);
        }
        result.add(maxHeap.peek());
        for (int i = size; i < num.length; i++) {
            maxHeap.remove(num[i - size]);
            maxHeap.add(num[i]);
            result.add(maxHeap.peek());
        }
        return result;
    }
}
