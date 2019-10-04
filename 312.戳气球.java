import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;


/*
 * @lc app=leetcode.cn id=312 lang=java
 *
 * [312] 戳气球
 */
class Solution {
    public int maxCoins(int[] nums) {
        /**
         * 每次让当前序列中的最小的数消失（除两头）
         */
        if (nums == null || nums.length == 0) {
            return 0;
        }
        PriorityQueue<Node> minHeap = new PriorityQueue<Node> (Comparator.comparingInt(a -> a.value));
        // 记录剩余的index
        List<Integer> remainingIndex = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(new Node(nums[i], i));
            remainingIndex.add(i);
        }
        // 记录当前边界
        int left = 0; 
        int right = nums.length - 1;
        int result = 0;
        while (minHeap.size() >= 3) {
            Node firstMinNode = minHeap.poll();
            int indexOfMin = firstMinNode.index;
            // if (indexOfMin == left) {
            //     Iterator<Node> iterator = minHeap.iterator();
            //     iterator.next(); // 首个元素
            //     Node node = iterator.next(); // 第二个元素
            //     if (node.index == right) {
            //         node = iterator.next(); // 第三个元素
            //     }
            //     indexOfMin = node.index;
            // }
            // if (indexOfMin == right) {
            //     Iterator<Node> iterator = minHeap.iterator();
            //     iterator.next(); 
            //     Node node = iterator.next(); 
            //     if (node.index == left) {
            //         node = iterator.next();
            //     }
            //     indexOfMin = node.index;
            // }
            if (firstMinNode == left || firstMinNode == right) {
                Node secondMinNode = minHeap.poll();
                indexOfMin = secondMinNode.index;
                if (secondMinNode == left || secondMinNode == right) {
                    indexOfMin = minHeap.poll().index;
                    minHeap.add(secondMinNode);
                }
                minHeap.add(firstMinNode);
            }
            result += nums[indexOfMin - 1] * nums[indexOfMin] * nums[indexOfMin + 1];
            minHeap.remove();
            remainingIndex.remove(Integer.valueOf(indexOfMin));
        }
        if (minHeap.size() == 2) {
            result += nums[left] * nums[right];
            result += Math.max(nums[left], nums[right]);
        }
        if (minHeap.size() == 1) {
            result += nums[0];
        }
        return result;
    }
    public class Node {
        int value;
        int index;
        Node (int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}

