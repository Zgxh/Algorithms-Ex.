//请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。它应该支持以下操作：get 和 put。 
//
// 
// get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。 
// put(key, value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效
//。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除最久未使用的键。 
// 
//
// 「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。 
//
// 
//
// 进阶： 
//你是否可以在 O(1) 时间复杂度内执行两项操作？ 
//
// 
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
// 👍 275 👎 0


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 方案1：利用TreeSet + HashMap
 * TreeSet查找和删除元素：O(logn)，用于对使用频率进行排序。
 * 具体：通过HashMap来判断集合中是否存在元素，然后对其更改。
 *
 * 需要注意的是：
 * TreeSet中的元素在更改时需要删掉再重新添加，否则不会触发比较器的比较。
 */

//leetcode submit region begin(Prohibit modification and deletion)
class LFUCache {

    private Map<Integer, Node> map;
    private TreeSet<Node> set;
    private int capacity;
    private int globalTime = 0; // 全局时间

    public LFUCache(int capacity) {
        this.map = new HashMap();
        this.set = new TreeSet<Node>((o1, o2) -> {
            return o1.frequency - o2.frequency == 0 ? o1.time - o2.time : o1.frequency - o2.frequency;
        }); // 先按频率比较，再按最后一次更改的时间比较
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        set.remove(node); // set中的元素要删了重加
        node.frequency++;
        node.time = globalTime;
        set.add(node);
        globalTime++;

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (map.containsKey(key)) {
            Node node = map.get(key);
            set.remove(node); // set中的元素要删了重加
            node.value = value;
            node.frequency++;
            node.time = globalTime;
            set.add(node);
        } else {
            if (map.size() == capacity) {
                Node node = set.pollFirst();
                map.remove(node.key);
            }
            Node node = new Node(key, value, globalTime);
            map.put(key, node);
            set.add(node);
        }
        globalTime++;
    }
}

class Node {
    int key;
    int value;
    int time;
    int frequency;
    Node (int key, int value, int time) {
        this.key = key;
        this.value = value;
        this.time = time;
        this.frequency = 1;
    }
}

/**
 * 方案2:利用两个HashMap+双向链表。
 * keyMap是存放key与结点Node的映射关系，freqMap存放频次freq与链表linkedList的映射关系；
 * 从keyMap中查询key对应的结点地址，在freqMap中找到该结点，删除纠结点，加入新结点：
 * 新结点的freq自增1，time更新为当前时间。
 * 链表的插入总是头插，代表结点的插入顺序，即插入时间。
 * 当容量超过capacity时，对最小freq对应的链表尾结点进行删除，因此需要额外维护一个minFreq来
 * 指示当前的最小频率。
 *
 * 该方案的get和put都为O(1)时间复杂度。
 * 注意：如果采用java.util.LinkedList来作为链表实现的话，删除某个结点的时间复杂度不为O(1)，
 * 因为需要遍历该链表来找到该结点。
 */

class LFUCache {

    private Map<Integer, Node> keyMap;
    private Map<Integer, MyLinkedList> freqMap;
    private int capacity;
    private int globalTime = 0; // 记录全局时间
    private int minFreq = 0; // 记录当前freqMap中的最小频率

    public LFUCache(int capacity) {
        this.keyMap = new HashMap();
        this.freqMap = new HashMap();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!keyMap.containsKey(key)) {
            return -1;
        }
        Node node = keyMap.get(key);
        // 从原freq对应的链表中删除结点
        deleteNodeFromLinkedList(node);
        // 增加频次，更改最后一次使用的时间，然后插入到对应的链表中
        node.freq++;
        node.time = globalTime;
        addNodeToLinkedList(node);
        globalTime++;

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        // 分为keyMap中有该key和无该key
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            deleteNodeFromLinkedList(node);
            node.freq++;
            node.time = globalTime;
            node.value = value;
            addNodeToLinkedList(node);
        } else {
            if (keyMap.size() == capacity) {
                Node tailNode = freqMap.get(minFreq).getLastNode();
                deleteNodeFromLinkedList(tailNode);
                keyMap.remove(tailNode.key);
            }
            Node node = new Node(key, value, globalTime);
            addNodeToLinkedList(node);
            minFreq = 1; // 新结点会使得minFreq变为1
            keyMap.put(key, node);
        }
        globalTime++;
    }

    // 把新的node放入对应频次的链表中的头结点位置
    private void addNodeToLinkedList(Node node) {
        MyLinkedList linkedList = null;
        if (!freqMap.containsKey(node.freq)) {
            linkedList = new MyLinkedList();
        } else {
            linkedList = freqMap.get(node.freq);
        }
        linkedList.addFirst(node);
        freqMap.put(node.freq, linkedList);
        // minFreq的更新条件：可能前一个freq映射已经被删掉
        if (!freqMap.containsKey(this.minFreq)) {
            this.minFreq++;
        }
    }

    // 从结点所在的链表中删除该结点
    private void deleteNodeFromLinkedList(Node node) {
        MyLinkedList linkedList = freqMap.get(node.freq);
        linkedList.removeNode(node);
        // 当链表中不再有有效结点时，则从freqMap中删除该映射
        if (linkedList.size == 0) {
            freqMap.remove(node.freq);
        }
    }
}

// 自定义的链表结点
class Node {
    int key;
    int value;
    int freq; // 使用频次
    int time; // 最后一次更改的时间
    Node pre;
    Node next;
    Node (int key, int value, int time) {
        this.key = key;
        this.value = value;
        this.freq = 1;
        this.time = time;
        this.pre = null;
        this.next = null;
    }
}

// 自定义的双向链表
class MyLinkedList {

    Node dummyHead;
    Node dummyTail;
    int size;

    MyLinkedList() {
        this.dummyHead = new Node(-1, -1, -1);
        this.dummyTail = new Node(-1, -1, -1);
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
        this.size = 0;
    }

    // 把某结点插入到该链表的头结点位置
    public void addFirst(Node node) {
        node.next = dummyHead.next;
        dummyHead.next.pre = node;
        dummyHead.next = node;
        node.pre = dummyHead;
        size++;
    }

    // 从该链表中删除某个结点
    public void removeNode(Node node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
        size--;
    }

    // 获取该链表的尾结点
    public Node getLastNode() {
        return dummyTail.pre;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
