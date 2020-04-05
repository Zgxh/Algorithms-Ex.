//设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。 
//
// get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。 
//put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平
//局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。 
//
// 一个项目的使用次数就是该项目被插入后对其调用 get 和 put 函数的次数之和。使用次数会在对应项目被移除后置为 0。 
//
// 进阶： 
//你是否可以在 O(1) 时间复杂度内执行两项操作？ 
//
// 示例： 
//
// LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回 1
//cache.put(3, 3);    // 去除 key 2
//cache.get(2);       // 返回 -1 (未找到key 2)
//cache.get(3);       // 返回 3
//cache.put(4, 4);    // 去除 key 1
//cache.get(1);       // 返回 -1 (未找到 key 1)
//cache.get(3);       // 返回 3
//cache.get(4);       // 返回 4 
// Related Topics 设计


//leetcode submit region begin(Prohibit modification and deletion)
class LFUCache {

    /**
     * 用hashMap存缓存，用堆存放使用次数。
     *
     */

    private int capacity = 0;
    private int index = 0;
    Map<Integer, Node> map;
    Queue<Node> minHeap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        minHeap = new PriorityQueue<>();
    }
    
    public int get(int key) {
        // 如果缓存中有该key，则使用次数+1
        Node got = map.get(key);
        if (got != null) {
            minHeap.remove(got);
            got.times++;
            index++;
            got.index = index;
            minHeap.offer(got);
            return got.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        // 若缓存中有该key，使用次数+1
        Node got = map.get(key);
        index++;
        if (got != null) {
            minHeap.remove(got);
            got.value = value;
            got.times++;
            got.index = index;
            minHeap.offer(got);
        } else { // 没有该key
            if (map.size() == capacity) { // 首先判断满没满，如果满了就拿走使用最少的
                Node min = minHeap.poll();
                map.remove(min.key);
            }
            Node newNode = new Node(key, value, 1, index);
            minHeap.offer(newNode);
            map.put(key, newNode);
        }
    }
}

class Node implements Comparable<Node> {

    int key;
    int value;
    int times;
    int index;

    public Node(int key, int value, int times, int index) {
        this.key = key;
        this.value = value;
        this.times = times;
        this.index = index;
    }

    @Override
    public int compareTo(Node o) {
        return this.times - o.times == 0 ? this.index - o.index : this.times - o.times;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
