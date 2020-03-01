import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 剑指offer第63题：数据流的中位数
 *
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取
 * 数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
 * @author Yu Yang
 * @create 2020-03-01 20:52
 */
public class Question_63 {

    /**
     * 思路：把数分成两组，大顶堆存小数，小顶堆存大数。新来一个数，比小顶堆大就放进小顶堆，比大顶堆小就放进大顶堆。
     * 始终维持两个堆size平衡，大顶堆可以比小顶堆size大1.当两个堆size不同时，一定是奇数个num，中位数是大顶堆的堆顶；
     * 两个堆size相同时，中位数中两个堆堆顶的平均数。
     */

    PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // 大顶堆存小数
    PriorityQueue<Integer> big = new PriorityQueue<>(); // 小顶堆存大数

    public void Insert(Integer num) {
        if (small.isEmpty() || num < small.peek()) {
            small.add(num);
        } else {
            big.add(num);
        }
        if (small.size() - big.size() > 1) {
            big.add(small.poll());
        } else if (big.size() > small.size()) {
            small.add(big.poll());
        }
    }

    public Double GetMedian() {
        if (small.size() != big.size()) {
            return (double)small.peek();
        } else {
            return (small.peek() + big.peek()) / 2.0;
        }
    }
}
